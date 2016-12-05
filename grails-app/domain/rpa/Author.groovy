package rpa

class Author {
    String name
    static belongsTo = [article: Article]

}
