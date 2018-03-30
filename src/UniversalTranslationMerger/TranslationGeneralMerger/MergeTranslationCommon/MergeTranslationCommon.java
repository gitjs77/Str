package UniversalTranslationMerger.TranslationGeneralMerger.MergeTranslationCommon;

import UniversalTranslationMerger.TranslationGeneralMerger.TranslationMergeService;

import java.io.File;
import java.io.IOException;

/**
 * @Author Jack <e.kobets>
 */
public class MergeTranslationCommon {
    final static String TRANSLATION_COMMON_FILE_SIGNATURE_REGEX = ".+;.+;.+;.+;.+";

    public static void main(String[] args) throws IOException {
        final String newTranslationsFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationCommon/newTranslationUa.csv";
        final String currentTranslationCommonFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationCommon/01--translation.csv";

        final TranslationMergeService translationMergeService = new TranslationMergeService();

        final File mergedTranslationMobile = translationMergeService.universalTranslationMerger(newTranslationsFile, currentTranslationCommonFile,
                3, 4, 2, 2);

        System.out.println("\nvalidateMergedFileByRegexSignature: "
                + translationMergeService.validateMergedFileByRegexSignature(mergedTranslationMobile.getPath(), TRANSLATION_COMMON_FILE_SIGNATURE_REGEX));
        }
}
