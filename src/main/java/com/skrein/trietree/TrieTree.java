//package com.skrein.trietree;
//
//import lombok.Data;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author: hujiansong
// * @Date: 2019/6/3 15:29
// * @since: 1.8
// */
//public class TrieTree {
//
//    private Node root;
//
//    /**
//     * 保存Node
//     *
//     * @param word
//     */
//    public synchronized void saveWord(String word) {
//        Node root = this.root;
//        for (int i = 0; i < word.length(); i++) {
//            char c = word.charAt(i);
//            Node searchNode = searchNode(c, root.getChildren());
//            if (searchNode == null) {
//                // 后续就直接构造，然后结束
//                for (int j = i; j < word.length(); j++) {
//                    Node newNode = new Node();
//                    newNode.setLetter(word.charAt(j));
//                    newNode.setPreCount(1);
//                    if (j == word.length() - 1) {
//                        newNode.setWordCount(1);
//                        newNode.setLeaf(true);
//                    }
//                    root.getChildren().add(newNode);
//                    root = newNode;
//                }
//                return;
//            } else {
//                // 找到了
//                if (i == word.length() - 1) {
//                    searchNode.setLeaf(true);
//                    searchNode.setWordCount(searchNode.getWordCount() + 1);
//                }
//                searchNode.setPreCount(searchNode.getPreCount() + 1);
//                root = searchNode;
//            }
//        }
//    }
//
//
//    /**
//     * 查找词组
//     *
//     * @param word
//     * @return
//     */
//    public Node searchHit(String word) {
//        Node tree = this.root;
//        for (int i = 0; i < word.length(); i++) {
//            Node searchNode = searchNode(word.charAt(i), tree.getChildren());
//            if (searchNode == null) {
//                return null;
//            } else {
//                if (i == word.length() - 1) {
//                    return searchNode;
//                } else {
//                    tree = searchNode;
//                }
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * 查找character属于哪个Node
//     *
//     * @param c
//     * @param childs
//     * @return
//     */
//    private Node searchNode(char c, Set<Node> childs) {
//        for (Node child : childs) {
//            if (child.getLetter().equals(c)) {
//                return child;
//            }
//        }
//        return null;
//    }
//
//    public TrieTree() {
//        this.root = new Node();
//    }
//
//    public static void main(String[] args) {
//        TrieTree tree = new TrieTree();
//        tree.saveWord("aacle");
//        tree.saveWord("abple");
//        tree.saveWord("abcd");
//        tree.saveWord("ab");
//        Node abple = tree.searchHit("ab");
//        if (abple == null) {
//            System.out.println("没有改字符串");
//        } else {
//            System.out.println("abple在文章中出现了" + abple.getWordCount() + "次数");
//            System.out.println("ab prefix在文章中出现了" + abple.getPreCount() + "次数");
//        }
//    }
//
//    @Data
//    static class Node {
//        /**
//         * 单词
//         */
//
//        Character letter;
//
//        /**
//         * 子结点
//         */
//        Set<Node> children = new HashSet<>();
//
//        /**
//         * 叶子结点，标识是否一个单词结束
//         */
//        boolean isLeaf;
//
//        /**
//         * 前缀的数量
//         * 每遍历一次都需要+1
//         */
//        int preCount;
//
//        /**
//         * 单词的个数
//         * 只有当是叶子结点的时候需要+1
//         */
//        int wordCount;
//    }
//}
