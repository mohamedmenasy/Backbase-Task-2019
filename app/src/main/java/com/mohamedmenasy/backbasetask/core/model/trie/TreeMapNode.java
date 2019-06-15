package com.mohamedmenasy.backbasetask.core.model.trie;


import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapNode<V> {

    private final char character;
    private boolean key;
    private V value;

    TreeMapNode(char character) {
        key = false;
        value = null;
        this.character = character;
        children = createMap();

    }

    TreeMapNode<V> addChild(final char character) {
        final TreeMapNode<V> leafNode = createNode(character);
        children.put(character, leafNode);
        return leafNode;
    }

    V getValue() {
        return value;
    }


    void setValue(V value) {
        this.value = value;
    }

    boolean isKey() {
        return key;
    }

    void setKey(boolean isKey) {
        this.key = isKey;
        if (!isKey) {
            value = null;
        }
    }

    private Map<Character, TreeMapNode<V>> onCreateMap() {
        return new TreeMap<>();
    }

    private TreeMapNode<V> onCreateNewNode(char character) {
        return new TreeMapNode<>(character);
    }


    private final Map<Character, TreeMapNode<V>> children;

    private Map<Character, TreeMapNode<V>> createMap() {
        return onCreateMap();
    }

    private TreeMapNode<V> createNode(char character) {
        return onCreateNewNode(character);
    }


    TreeMapNode<V> getChild(final char character) {
        if (children.containsKey(character)) {
            return children.get(character);
        } else {
            return null;
        }
    }

    boolean containsChild(final char c) {
        return children.containsKey(c);
    }

    Collection<TreeMapNode<V>> getChildren() {
        return children.values();
    }

    boolean isEnd() {
        return children.values().isEmpty();
    }

}
