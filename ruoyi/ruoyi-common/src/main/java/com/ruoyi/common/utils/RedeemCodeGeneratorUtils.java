package com.ruoyi.common.utils;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * 序列号工具类
 *
 * @author ruoyi
 */
public class RedeemCodeGeneratorUtils
{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateCode(int codeLength,Set<String> existingCodes) {
        StringBuilder code = new StringBuilder();
        SecureRandom random = new SecureRandom();
        while (true) {
            for (int i = 0; i < codeLength; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                code.append(CHARACTERS.charAt(randomIndex));
            }

            if (!existingCodes.contains(code.toString())) {
                existingCodes.add(code.toString());
                break;
            }

            code.setLength(0);
        }

        return code.toString();
    }

    public static void main(String[] args) {
        Set<String> existingCodes = new HashSet<>();
        System.out.println(generateCode(15,existingCodes));
    }
}
