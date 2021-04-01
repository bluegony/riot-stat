package com.study.algorithm.skpcoding.coding;

import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2017. 11. 21..
 */
@Slf4j
public class TrieNode {
    final static int alphabets = 26;
    TrieNode[] children;
    int toNumber(char ch) {
        return ch-'a';
    }
    int terminal;
    TrieNode[] fail;

    public TrieNode() {
//        this.terminal = false;
        this.terminal = -1;
        this.children = new TrieNode[alphabets];
    }
//    public void insert(char[] keyArray, int i, int id) {
//        if(i>=keyArray.length)
//            terminal = id;
//        else {
//                if(children == null )
//                    children = new TrieNode[alphabets];
//            int next = toNumber(keyArray[i]);
//            if(children[next] == null)
//                children[next] = new TrieNode();
//            children[next].insert(keyArray, i+1);
//        }
//    }
    public void insert(String word, int id) {
        TrieNode p = this;
        if(p.children == null) {
            log.info("null1");
            p.children = new TrieNode[alphabets];
        }
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c-'a';
            if(p.children==null) {
                log.info("null");
            }
            log.info("{} {}",index,id);
            if(p.children[index]==null){
                TrieNode temp = new TrieNode();
                p.children[index]=temp;
                p = temp;
            }else{
                p=p.children[index];
            }
        }
        log.info("{}",id);
        p.terminal=id;
    }
    public TrieNode find(char[] keyArray, int i) {
        if(i>=keyArray.length)
            return this;
        int next = toNumber(keyArray[i]);
        if(children[next] == null) return null;
        return children[next].find(keyArray, i+1);
    }
    public void print() {
        for(int i=0; i<alphabets; ++i) {
            if(children!=null && children[i]!=null) {
                log.info("{}, terminal id = {}", (char) (i + 'a'), terminal);
                children[i].print();
            }
        }
    }
}
