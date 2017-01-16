package com.hzih.ca.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-10-13.
 */
public class CertUtils {
    public static String getPartFromDN(String dn, String dnpart) {
        String part = null;
        final List<String> dnParts = getPartsFromDNInternal(dn, dnpart, true);
        if (!dnParts.isEmpty()) {
            part = dnParts.get(0);
        }
        return part;
    }

    public static List<String> getPartsFromDNInternal(final String dn, final String dnPart, final boolean onlyReturnFirstMatch) {
        final List<String> parts = new ArrayList<String>();
        if (dn != null && dnPart != null) {
            final String dnPartLowerCase = dnPart.toLowerCase();
            final int dnPartLenght = dnPart.length();
            boolean quoted = false;
            boolean escapeNext = false;
            int currentStartPosition = -1;
            for (int i = 0; i < dn.length(); i++) {
                final char current = dn.charAt(i);
                // Toggle quoting for every non-escaped "-char
                if (!escapeNext && current == '"') {
                    quoted = !quoted;
                }
                // If there is an unescaped and unquoted =-char we need to investigate if it is a match for the sought after part
                if (!quoted && !escapeNext && current == '=' && dnPartLenght <= i) {
                    // Check that the character before our expected partName isn't a letter (e.g. dnsName=.. should not match E=..)
                    if (i - dnPartLenght - 1 < 0 || !Character.isLetter(dn.charAt(i - dnPartLenght - 1))) {
                        boolean match = true;
                        for (int j = 0; j < dnPartLenght; j++) {
                            if (Character.toLowerCase(dn.charAt(i - dnPartLenght + j)) != dnPartLowerCase.charAt(j)) {
                                match = false;
                                break;
                            }
                        }
                        if (match) {
                            currentStartPosition = i + 1;
                        }
                    }
                }
                // When we have found a start marker, we need to be on the lookout for the ending marker
                if (currentStartPosition != -1 && ((!quoted && !escapeNext && (current == ',' || current == '+')) || i == dn.length() - 1)) {
                    int endPosition = (i == dn.length() - 1) ? dn.length() - 1 : i - 1;
                    // Remove white spaces from the end of the value
                    while (endPosition > currentStartPosition && dn.charAt(endPosition) == ' ') {
                        endPosition--;
                    }
                    // Remove white spaces from the beginning of the value
                    while (endPosition > currentStartPosition && dn.charAt(currentStartPosition) == ' ') {
                        currentStartPosition++;
                    }
                    // Only return the inner value if the part is quoted
                    if (dn.charAt(currentStartPosition) == '"' && dn.charAt(endPosition) == '"') {
                        currentStartPosition++;
                        endPosition--;
                    }
                    parts.add(dn.substring(currentStartPosition, endPosition + 1));
                    if (onlyReturnFirstMatch) {
                        break;
                    }
                    currentStartPosition = -1;
                }
                if (escapeNext) {
                    // This character was escaped, so don't escape the next one
                    escapeNext = false;
                } else {
                    if (!quoted && current == '\\') {
                        // This escape character is not escaped itself, so the next one should be
                        escapeNext = true;
                    }
                }
            }
        }
        return parts;
    }
}
