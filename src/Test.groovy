import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @Author Jack <e.kobets>
 * 10/2/17
 */
class Test {
    public static void main(String[] args) {
        String input = "public String field1;String field2;" +
                "public String field4; field4";

        def fields = input.trim().tokenize(';');
//        fields.collect { i -> i.split().getAt(2) }.join(', ')

//        def result = fields.collect { i -> i.split().getAt(2) }.join(', ')
//        println(result)

//        Stream.of(fields).forEach{i -> System.out.println(i)};
//
//        fields.each {i -> println(i)}
        /*final String ERROR_FIELD =
                '\\n=========================ERROR=========================' +
                        '\\n*' +
                        '\\nWRONG field declaration!!' +
                        '\\nTODO Use correct syntax:' +
                        '\\nprivate|protected|public Object fieldName;' +
                        '\\n\\tor:' +
                        '\\nObject fieldName;' +
                        '\\nAll fields must be separated by \";\" delimiter!!' +
                        '\\n========================================================\\n';
        def result = fields.collect { i ->
            (i.tokenize()[2] == null) ?
                    (i.tokenize()[1] == null) ? ERROR_FIELD : i.tokenize()[1] :
                    i.tokenize()[2] }.join(', ')

        println(result)*/

//        println(fields.getAt(1).tokenize().getAt(2) == null ? "null" : "2")
//        String ERROR_FIELD = 'error';
        final String ERROR_FIELD =
                '\n=========================ERROR=========================' +
                        '\n/**' +
                        '\nWRONG field declaration!!' +
                        '\nTODO Use correct field syntax:' +
                        '\nprivate|protected|public Object fieldName;' +
                        '\n\tor:' +
                        '\nObject fieldName;' +
                        '\nAll fields must be separated by \";\" delimiter!!*/' +
                        '\n========================================================\\n';
        String result = fields.stream()
                .filter{i -> !i.contains('class') || !i.isEmpty()}
                .map{ i ->
            (i.tokenize()[2] == null) ?
                    (i.tokenize()[1] == null) ? ERROR_FIELD : i.tokenize()[1] :
                    i.tokenize()[2] }.collect(Collectors.joining(', '));

        println(result)

    }
}
