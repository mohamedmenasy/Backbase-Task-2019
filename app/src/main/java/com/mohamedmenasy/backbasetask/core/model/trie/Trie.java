package com.mohamedmenasy.backbasetask.core.model.trie;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Trie<V> {
  char ROOT_KEY = ' ';

  private TreeMapNode<V> root;

  public Trie() {
    root = createRootNode();
  }

  private TreeMapNode<V> createRootNode() {
    return onCreateRootNode();
  }

  private TreeMapNode<V> onCreateRootNode() {
    return new TreeMapNode<>(ROOT_KEY);
  }

  public void insert(String key, V value) {
    if (key == null) {
      return;
    }
    key = trimLowercaseString(key);

    TreeMapNode<V> crawler = root;
    for (int i = 0; i < key.length(); i++) {
      final char c = key.charAt(i);
      if (!crawler.containsChild(c)) {
        crawler = crawler.addChild(c);
      } else {
        crawler = crawler.getChild(c);
      }
    }
    crawler.setKey(true);
    crawler.setValue(value);
  }

  public List<V> getValueSuggestions(String prefix) {
    if (prefix == null) {
      return Collections.emptyList();
    }
    prefix = trimLowercaseString(prefix);

    TreeMapNode<V> crawler = root;
    for (int i = 0; i < prefix.length(); i++) {
      final char c = prefix.charAt(i);
      if (crawler.containsChild(c)) {
        crawler = crawler.getChild(c);
      } else {
        break;
      }
    }

    final List<V> suggestions = new LinkedList<>();
    findValueSuggestions(crawler, suggestions);
    return suggestions;
  }

  private void findValueSuggestions(final TreeMapNode<V> trieNode, final List<V> suggestions) {
    if (trieNode == null) {
      return;
    }
    if (trieNode.isKey()) {
      final V value = trieNode.getValue();
      if (value != null) {
        suggestions.add(value);
      } else {
        System.out.println("Null value for a key encountered");
      }
    }
    if (trieNode.isEnd()) {
      return;
    }
    for (final TreeMapNode<V> child : trieNode.getChildren()) {
      findValueSuggestions(child, suggestions);
    }
  }

  public int size() {
    return size(root);
  }

  public void deleteKey(final String key) {
    if (key == null) {
      return;
    }
    deleteKey(root, trimLowercaseString(key), 0);
  }

  private boolean deleteKey(TreeMapNode<V> node, final String word, int index) {
    if (word == null) {
      return false;
    }
    if (index == word.length()) {
      if (node.isKey()) {
        node.setKey(false);
        return true;
      } else {
        // No word found
        return false;
      }
    }
    for (final TreeMapNode<V> child : node.getChildren()) {
      if (child.getChar() == word.charAt(index)) {
        if (deleteKey(child, word, index + 1) && child.isEnd() && !child.isKey()) {
          node.removeChild(child.getChar());
          return true;
        }
      }
    }
    return false;
  }

  private int size(final TreeMapNode<V> node) {
    int sum = 0;
    if (node.isKey()) {
      sum = 1;
    }
    for (final TreeMapNode<V> child : node.getChildren()) {
      sum += size(child);
    }
    return sum;
  }

  private String trimLowercaseString(String key) {
    return key.toLowerCase().trim();
  }
}
