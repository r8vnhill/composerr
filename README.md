# StraitJakt: Validate Your Data with Precision

[![License](https://img.shields.io/badge/License-BSD_2--Clause-orange.svg)](https://opensource.org/licenses/BSD-2-Clause)
[![Maven Central](https://img.shields.io/maven-central/v/cl.ravenhill/strait-jakt.svg)](https://search.maven.org/artifact/cl.ravenhill/strait-jakt)

**StraitJakt** is a versatile Kotlin library designed to apply precise constraints to your data, ensuring it remains consistent and valid throughout your application.

## Features

- **Type-Safe Constraints**: Enforce rules on your data using Kotlin's powerful type system.
- **Declarative Syntax**: Easily define validation rules in a readable and expressive manner.
- **Comprehensive Validation**: Cover a wide array of data validation scenarios from simple to complex.
- **Customizable**: Extend or create custom constraints tailored to your needs.

## Getting Started

### Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("cl.ravenhill:strait-jakt:1.4.0")    // Replace with latest version
}
```

### Usage Example

Validate user registration data with StraitJakt by defining constraints within a `constraints` block:

```kotlin
fun registerUser(username: String, name: String, age: Int, password: String) {
    constraints {
        "Username should only contain alphanumeric characters" {
            username must BeAlphanumeric
        }
        "Username should be between 5 to 20 characters in length." {
            username must HaveLength { it in 5..20 }
        }
        "Username can only have underscores or hyphens as special characters" {
            username must Match(Regex("^[a-zA-Z0-9_-]*$"))
        }
        "Name field cannot be left blank" {
            name mustNot BeEmpty
        }
        "Users should be 18 years of age or older" {
            age must BeAtLeast(18)
        }
        "Password needs to be a minimum of 8 characters" {
            password must HaveLength { it >= 8 }
        }
        "Password should include at least one uppercase letter" {
            password must Contain(Regex("[A-Z]"))
        }
        "Password should include at least one lowercase letter" {
            password must Contain(Regex("[a-z]"))
        }
        "Password should include at least one digit" {
            password must Contain(Regex("[0-9]"))
        }
    }
    // Further implementation details...
}
```

Here's how you can handle validation scenarios:

```kotlin
try {
    registerUser("johndoe", "John Doe", 17, "password")
} catch (e: CompositeException) {
    println(e.message)
    // Handle multiple validation failures
}
```

### Exception Handling

StraitJakt throws a `CompositeException` when multiple constraints fail, allowing you to handle exceptions in a granular and informative way.

## Contributing

We welcome contributions! Please see our [Contribution Guidelines](CONTRIBUTING.md) for more information.

## License

StraitJakt is licensed under the [BSD 2-Clause License](LICENSE).
