# Impact Take Home Test - Number Range Summarizer

This is my submission for the Impact Junior Software Engineer take home test.

## Getting Started

Unit tests can be run by using `mvn test`.

I have **not** included an app that can be used to play with Summarizer (didn't seem relevant to spec), but wouldn't mind doing so upon request.

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

## Fin

Thank you very much for this opportunity! I am available to answer any specific questions.

## Built with

- [Java](https://www.java.com/en/)
- [Maven](https://maven.apache.org/) - The build tool used

## Authors

* **Daniel Olivier** - [GitHub](https://github.com/olivier-danielj)
