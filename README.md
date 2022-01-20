# Impact Take Home Test - Number Range Summarizer

This is my submission for the Impact Junior Software Engineer take home test.

## Getting Started

Change directory to the project root before attempting the following.

Unit tests can be run by using `mvn test`.

The Sandbox, which will allow you to enter user input and see the resulting summary, can be accessed by using 

```
mvn clean package install exec:java
```

## Number Range Summarizer

The Number Range Summarizer is an interface built to summarize Collections of Integers by collapsing ranges of sequential values.

[Summarizer](https://github.com/olivier-danielj/impact-test-summarizer/blob/main/src/main/java/numberrangesummarizer/Summarizer.java) implements NumberRangeSummarizer and comes with two methods: _collect_ and _summarizeCollection_

### Collect

```java
Collection<Integer> collect(String input)
```

Converts a comma delimited list of numbers into an Integer Collection.

#### Example

```
collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31")
```

returns a Collection of the form

```
[1,3,6,7,8,12,13,14,15,21,22,23,24,31]
```

and throws an _IllegalArgumentException_ if the input does not follow the correct format.

### summarizeCollection

```java
String summarizeCollection(Collection<Integer> input)
```

Returns a string summary of an Integer Collection, collapsing ranges of sequential numbers.

#### Example

Given a collection of the form

```
[1,3,6,7,8,12,13,14,15,21,22,23,24,31]
```

summarizeCollection returns

```
"1, 3, 6-8, 12-15, 21-24, 31"
```

We assume the input format is correct, but summarizeCollection will sort and remove duplicates.

## Sandbox

The Sandbox is a simple user interface that enables interaction with the Summarizer. Details on running the sandbox have been provided in the *Getting Started* section of this readme.

The Sandbox can take Terminal or Argument input and can also perform a demo on default values. The input mode can be changed by editing the MODE constant in Sandbox.java

The Sandbox output can also be limited by disabling the VERBOSE flag in Sandbox.java

Please note that the Sandbox has not been fully tested, it is just a bonus feature.

## Fin

Thank you very much for this opportunity! I am available to answer any specific questions.

## Built with

- [Java](https://www.java.com/en/)
- [Maven](https://maven.apache.org/) - The build tool used

## Authors

* **Daniel Olivier** - [GitHub](https://github.com/olivier-danielj)
