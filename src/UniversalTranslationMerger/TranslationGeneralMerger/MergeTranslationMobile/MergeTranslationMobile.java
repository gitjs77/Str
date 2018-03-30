package UniversalTranslationMerger.TranslationGeneralMerger.MergeTranslationMobile;


import UniversalTranslationMerger.TranslationGeneralMerger.TranslationMergeService;

import java.io.File;
import java.io.IOException;

/**
 * @Author Jack <e.kobets>
 * 3/30/18
 */
public class MergeTranslationMobile {
    final static String TRANSLATION_MOBILE_FILE_SIGNATURE_REGEX = ".+;.+;.+;.+;.+";

    public static void main(String[] args) throws IOException {
        final String newTranslationsFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationMobile/newTranslationUa.csv";
        final String currentTranslationMobileFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationMobile/01--translation.mobile.csv";

        final TranslationMergeService translationMergeService = new TranslationMergeService();

        final File mergedTranslationMobile = translationMergeService.universalTranslationMerger(newTranslationsFile, currentTranslationMobileFile,
                3, 3, 2, 2);

        System.out.println("\nvalidateMergedFileByRegexSignature: "
                + translationMergeService.validateMergedFileByRegexSignature(mergedTranslationMobile.getPath(), TRANSLATION_MOBILE_FILE_SIGNATURE_REGEX));

    }
}
