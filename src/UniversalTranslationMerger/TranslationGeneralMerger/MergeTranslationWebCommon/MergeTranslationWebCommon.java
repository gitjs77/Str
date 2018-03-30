package UniversalTranslationMerger.TranslationGeneralMerger.MergeTranslationWebCommon;

import UniversalTranslationMerger.TranslationGeneralMerger.TranslationMergeService;

import java.io.File;
import java.io.IOException;

/**
 * @Author Jack <e.kobets>
 * 3/31/18
 */
public class MergeTranslationWebCommon {
    final static String TRANSLATION_WEB_COMMON_FILE_SIGNATURE_REGEX = ".+;.+;.+;.+;.+";

    public static void main(String[] args) throws IOException {
        final String newTranslationsFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationWebCommon/newTranslationUa.csv";
        final String currentTranslationWebCommonFile = "src/UniversalTranslationMerger/TranslationGeneralMerger/MergeTranslationWebCommon/01--translation.web.csv";

        final TranslationMergeService translationMergeService = new TranslationMergeService();

        final File mergedTranslationMobile = translationMergeService.universalTranslationMerger(newTranslationsFile, currentTranslationWebCommonFile,
                3, 3, 2, 2);

        System.out.println("\nvalidateMergedFileByRegexSignature: "
                + translationMergeService.validateMergedFileByRegexSignature(mergedTranslationMobile.getPath(), TRANSLATION_WEB_COMMON_FILE_SIGNATURE_REGEX));
    }
}
