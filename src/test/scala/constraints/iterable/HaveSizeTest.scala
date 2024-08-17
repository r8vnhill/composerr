package cl.ravenhill.composerr
package constraints.iterable

import org.scalacheck.{Arbitrary, Gen}
import matchers.{beTrue, beFalse}

class HaveSizeTest extends AbstractComposerrTest {
  "A HaveSize constraint" - {
    "when created" - {
      "with a size" - {
        "should have a validator that returns true if the collection has the specified size" in {
          forAll(arbIterableAndSize(Arbitrary.arbInt.arbitrary)) { (tuple) =>
            val (iterable, size) = tuple
            val constraint = new HaveSize[Any](size)
            constraint.validator(iterable) should beTrue
          }
        }

        "should have a validator that returns false if the collection has a different size" in {
          forAll(
            arbIterable(Arbitrary.arbInt.arbitrary, Gen.chooseNum(0, 100)),
            Arbitrary.arbInt.arbitrary
          ) { (iterable, size) =>
            whenever(iterable.size != size) {
              val constraint = new HaveSize[Any](size)
              constraint.validator(iterable) should beFalse
            }
          }
        }
      }

      "with a predicate" - {
        "should have a validator that returns true if the collection has the specified size" in {
          forAll(arbIterable(arbAnyPrimitive, Gen.chooseNum(1, 100))) { iterable =>
            val constraint = new HaveSize[Any](_ > 0)
            constraint.validator(iterable) should beTrue
          }
        }
        
        "should have a validator that returns false if the collection has a different size" in {
          forAll(arbIterable(arbAnyPrimitive, Gen.chooseNum(11, 100))) { iterable =>
            val constraint = new HaveSize[Any](_ < 10)
            constraint.validator(iterable) should beFalse
          }
        }
      }
    }
  }
}

/** Generates an arbitrary `HaveSize` constraint with a specified size.
 *
 * This generator produces a `HaveSize` instance that constrains a collection to a specific size. The size is determined
 * by the provided `sizeGen` generator, which by default generates an integer between 0 and 100.
 *
 * @param sizeGen A generator that determines the size constraint for the `HaveSize` instance. Defaults to a generator
 *                that produces an integer between 0 and 100.
 * @tparam T The type of elements in the collection that the `HaveSize` constraint will be applied to.
 * @return A generator that produces a `HaveSize` instance with a size constraint determined by `sizeGen`.
 */
def arbHaveSize[T](sizeGen: Gen[Int] = Gen.chooseNum(0, 100)): Gen[HaveSize[T]] = sizeGen.map(new HaveSize[T](_))

/** Generates a tuple containing an arbitrary collection of elements and its size.
 *
 * This generator produces a random collection of elements of type `T` along with its size. The size is randomly chosen
 * between 0 and 100, and the collection is generated with exactly that number of elements.
 *
 * @param element A generator for the type of elements that will populate the collection.
 * @tparam T The type of the elements in the collection.
 * @return A generator that produces a tuple where the first element is a collection of `T` and the second element is
 *         the size of the collection.
 */
private def arbIterableAndSize[T](element: Gen[T]): Gen[(Iterable[T], Int)] = for {
  size <- Gen.choose(0, 100)
  elements <- Gen.listOfN(size, element)
} yield (elements, size)
