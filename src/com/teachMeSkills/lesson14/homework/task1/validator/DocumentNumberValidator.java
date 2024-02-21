package com.teachMeSkills.lesson14.homework.task1.validator;

import com.teachMeSkills.lesson14.homework.task1.consts.FormatConstants;
import com.teachMeSkills.lesson14.homework.task1.consts.ExceptionMessageConstants;
import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;
import com.teachMeSkills.lesson14.homework.task1.exceptions.DocumentNumberFormatException;
import com.teachMeSkills.lesson14.homework.task1.service.WriteFileService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentNumberValidator {

    public static boolean isDocumentNumberValid(String documentNumber) {
        try {
            if (isDocumentNumberLengthValid(documentNumber)
                    && isDocumentNumberStartsFromValidChars(documentNumber)
                    && isDocumentNumberMatchesRegex(documentNumber)) {
                System.out.println(documentNumber + " - is VALID number");
                WriteFileService.writeFile(PathConstants.EXECUTION_LOG, documentNumber + " - is VALID number");
                return true;
            }
        } catch (DocumentNumberFormatException e) {
            String logMessage = documentNumber + " - is INVALID number: " + e.getMessage();
            System.out.println(logMessage);
            WriteFileService.writeFile(PathConstants.INVALID_NUMBERS_LOG, logMessage);
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, logMessage);
        } catch (Exception e) {
            String message = ExceptionMessageConstants.UNEXPECTED_EXCEPTION;
            System.out.println(message);
            WriteFileService.writeFile(PathConstants.ERROR_LOG, message);
        }
        return false;
    }

    public static boolean isDocNumber(String documentNumber) {
        return documentNumber.startsWith(FormatConstants.DOC_NUM);
    }

    private static boolean isDocumentNumberLengthValid(String documentNumber) throws DocumentNumberFormatException {
        int documentNumberLength = documentNumber.length();
        if (documentNumberLength == FormatConstants.DOC_NUMBER_LENGTH) {
            return true;
        } else {
            String message = String.format(ExceptionMessageConstants.DOCUMENT_NUMBER_LENGTH, documentNumberLength);
            throw new DocumentNumberFormatException(message);
        }
    }

    private static boolean isDocumentNumberStartsFromValidChars(String documentNumber) throws DocumentNumberFormatException {
        if (documentNumber.startsWith(FormatConstants.DOC_NUM) || documentNumber.startsWith(FormatConstants.CONTRACT)) {
            return true;
        } else {
            String message = String.format(ExceptionMessageConstants.START_CHARS, FormatConstants.DOC_NUM, FormatConstants.CONTRACT);
            throw new DocumentNumberFormatException(message);
        }
    }

    private static boolean isDocumentNumberMatchesRegex(String documentNumber) throws DocumentNumberFormatException {
        Pattern p = Pattern.compile(FormatConstants.REGEX);
        Matcher m = p.matcher(documentNumber);
        if (m.matches()) {
            return true;
        } else {
            String message = String.format(ExceptionMessageConstants.REGEX_MATCHES, FormatConstants.REGEX);
            throw new DocumentNumberFormatException(message);
        }
    }
}
