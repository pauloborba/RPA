package rpa

import groovy.transform.EqualsAndHashCode

/**
 * Created by rlsma on 26/11/16.
 */
@EqualsAndHashCode(includes='name')
class Author {
    String name
    static belongsTo = [article:Article]
}
