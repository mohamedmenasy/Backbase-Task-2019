# Mobile assignment RnD

A Backbase assignment for Android engineering positions provided by Mohamed Nabil.

## Features

### Non-technical features

- User can search for a city by name.
- A map shows the city location.
- Info screen displays all data for a city.
- A dynamic UI that can adapt the screen orientation.

### Technical features

- Built with MVP (Model–view–presenter).
- Used Jackson for [fast deserialization](https://github.com/fabienrenaud/java-json-benchmark) of the cities.json file.
- Used a Prefix Trie data structure (because it is has a very effective retrieval time).

## Trie Algorithm

[Trie](http://en.wikipedia.org/wiki/Trie) is an efficient information re**Trie**val data structure. Using Trie, search complexities can be brought to optimal limit (*key length*).

Every node of Trie consists of multiple branches. Each branch represents a possible character of keys. We need to mark the last node of every key as end of word node.

##### Insertion

Inserting a key into Trie is a simple approach. Every character of the input key is inserted as an individual Trie node. If the input key is new or an extension of the existing key, we need to construct non-existing nodes of the key, and mark end of the word for the last node. If the input key is a prefix of the existing key in Trie, we simply mark the last node of the key as the end of a word. The key length determines Trie depth.

##### Searching 

Searching for a key is similar to insert operation, however, we only compare the characters and move down. The search can terminate due to the end of a string or lack of key in the trie.

##### Complexity

Insert and search costs `O(key_length)`, however the memory requirements of Trie is `O(ALPHABET_SIZE * key_length * N)` where N is number of keys in Trie.

## Running the tests

There are many test cases covers the testing of Trie implementation, presenters and ui test of all activities using espresso.

##### Unit Tests

###### To run the Trie implementation test 

```bash
./gradlew testDebugUnitTest --tests com.mohamedmenasy.backbasetask.data.TrieTest
```

###### To run the presenter tests 

```bash
./gradlew testDebugUnitTest --tests com.mohamedmenasy.backbasetask.presenter.*
```

##### UI tests 

###### To run MainActivityTest:

```
$ adb shell am instrument -w -r -e debug false -e class 'com.mohamedmenasy.backbasetask.view.MainActivityTest' com.mohamedmenasy.backbasetask.test/androidx.test.runner.AndroidJUnitRunner
```

###### To run InfoActivityTest:

```
$ adb shell am instrument -w -r -e debug false -e class 'com.mohamedmenasy.backbasetask.view.InfoActivityTest' com.mohamedmenasy.backbasetask.test/androidx.test.runner.AndroidJUnitRunner
```

###### To run AboutActivityTest:

```
$ adb shell am instrument -w -r -e debug false -e class 'com.mohamedmenasy.backbasetask.view.AboutActivityTest' com.mohamedmenasy.backbasetask.test/androidx.test.runner.AndroidJUnitRunner
```

## Author

- **Mohamed Nabil** - *Initial work* - [linkedin](<https://www.linkedin.com/in/mohamedmenasy/>)