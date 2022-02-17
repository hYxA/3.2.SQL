package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @SneakyThrows
    public static void cleanData() {
        val queryRunner = new QueryRunner();
        val deleteUsers = "DELETE FROM users";
        val deleteCards = "DELETE FROM cards";
        val deleteCodes = "DELETE FROM auth_codes";

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                )
        ) {
            queryRunner.update(conn, deleteUsers);
            queryRunner.update(conn, deleteCards);
            queryRunner.update(conn, deleteCodes);
        }
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("petya", "qwerty123456");
    }

    @Value
    public static class Card {
        private String cardNumber;
    }

    public static Card getReceiverCardNumber() {
        return new Card("5559 0000 0000 0001");
    }

    public static Card getDonorCardNumber() {
        return new Card("5559 0000 0000 0002");
    }

    @Value
    public static class VerificationCode {
        private String verificationCode;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode() {
        val verificationCode = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1;";
        val queryRunner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            queryRunner.execute(conn, verificationCode, 1);
        }
        return null;
    }
}

