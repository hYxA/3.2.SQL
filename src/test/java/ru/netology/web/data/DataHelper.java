package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;

public class DataHelper {

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
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("petya", "qwerty123456");
    }

    public static AuthInfo getBadAuthInfo() {
        return new AuthInfo("petya123", "qwerty444");
    }

    @Value
    public static class Card {
        String cardNumber;
    }

    public static Card getReceiverCardNumber() {
        return new Card("5559 0000 0000 0001");
    }

    public static Card getDonorCardNumber() {
        return new Card("5559 0000 0000 0002");
    }

    @Value
    public static class VerificationCode {
        String verificationCode;
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

    public static String getBadVerificationCode() {
        return "098762";
    }

    @SneakyThrows
    public static String getUserID(String login) {
        val userID = "SELECT id FROM users WHERE login = ?;";
        val queryRunner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            queryRunner.execute(conn, userID, login);
        }
        return null;
    }
}

