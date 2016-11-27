package rpa

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='name')
class Author {
    String name
    static belongsTo = [article:Article]
}