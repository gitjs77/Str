/**
 * @Author Jack <e.kobets>
 */
class MapInit {
    static void main(String[] args) {

//        String input = "Type, fixed; Name, Victor";
//
//        def fields = input.trim().tokenize(';');
//        def result = fields.collect { i ->
//            'put(' + i.tokenize()[0] + ' ' + i.tokenize()[1] + ')'
//        }.join(';\n');
//        def mapStart = 'Map<String, String> created = new HashMap<String, String>(){{\n'
//        def mapEnd = '\n}};'
//        result += ';';
//        println(mapStart + result + mapEnd);

        String input = "Type, fixed; Name, Viktor";

        def fields = input.trim().tokenize(';');
        final String ERROR_FIELD =
                '\\n=========================ERROR=========================' +
                        '\\n/**' +
                        '\\nWRONG element declaration!!' +
                        '\\nTODO Use correct syntax:' +
                        '\\n\"key\", \"value\"; ' + '\"key2\", \"value2\";' +
                        '\\nAll key and value must be separated by \",\" delimiter!!' +
                        '\\nAll map elements must be separated by \";\" delimiter!!*/' +
                        '\\n========================================================\\n';
        def result = fields.collect { i ->
            (i.tokenize(', ')[0] == null || i.tokenize(', ')[1] == null) ? ERROR_FIELD :
            'put(' +
                    '\"' + i.tokenize(', ')[0] + '\"' +
                    ', ' +
                    '\"' + i.tokenize(', ')[1] + '\")'
        }.join(';\n');

        result += ';';

        println(result);
    }

}
