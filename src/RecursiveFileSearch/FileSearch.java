package RecursiveFileSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Author Jack <e.kobets>
 * 11/14/17
 */
public class FileSearch {

    public List<String> find(File dir, List<String> resaultArray, String keyWord) throws IOException {
        File file = new File(dir.getPath());
        if (!file.exists()) {
            throw new FileNotFoundException("Указанный файл отсуствует");
        }
        //get list of files in the current dir
        File[] files = file.listFiles();

        //if length of the File[] array == 0   ist mean that dir is empty
        if (files.length == 0) {
            System.out.println("Dir " + dir + " is empty");
        }
        //move trough array of the inner files of current dir
        for (int i = 0; i < files.length; i++) {
            //check is dir the i-file
            if (files[i].isDirectory()) {
                //check equals keyWord and i-file
                if (files[i].getName().equalsIgnoreCase(keyWord)) {
                    //append founded file name to the List<String> resaultArray
                    resaultArray.add("--------------------------------------\n" + "File " + "\"" + files[i].getName()
                            + "\"" + " found in:  " + files[i].getAbsolutePath());
                }
                //recursive call this method for the forks directories
                find(files[i], resaultArray, keyWord);
            }
        }
        return resaultArray;
    }
}
