package util;

import java.util.Scanner;

public class Helper {
    /**
     * Untuk konversi angka bulan (1-12) jadi nama bulan
     */
    public static String getNamaBulan(int bulan) {
        String namaBulan = "";
        switch (bulan) {
            case 1:
                namaBulan = "Januari";
                break;
            case 2:
                namaBulan = "Februari";
                break;
            case 3:
                namaBulan = "Maret";
                break;
            case 4:
                namaBulan = "April";
                break;
            case 5:
                namaBulan = "Mei";
                break;
            case 6:
                namaBulan = "Juni";
                break;
            case 7:
                namaBulan = "Juli";
                break;
            case 8:
                namaBulan = "Agustus";
                break;
            case 9:
                namaBulan = "September";
                break;
            case 10:
                namaBulan = "Oktober";
                break;
            case 11:
                namaBulan = "November";
                break;
            case 12:
                namaBulan = "Desember";
                break;
            default:
                namaBulan = "Bulan tidak valid";
                break;
        }
        return namaBulan;
    }

    /**
     * Untuk mengkonversi nama bulan (januari-desember) jadi angka (1-12)
     */
    public static int getBulanDariNama(String namaBulan) {
        String bulanLower = namaBulan.toLowerCase();
        switch (bulanLower) {
            case "januari":
                return 1;
            case "februari":
                return 2;
            case "maret":
                return 3;
            case "april":
                return 4;
            case "mei":
                return 5;
            case "juni":
                return 6;
            case "juli":
                return 7;
            case "agustus":
                return 8;
            case "september":
                return 9;
            case "oktober":
                return 10;
            case "november":
                return 11;
            case "desember":
                return 12;
            default:
                return -1;
        }
    }

    /**
     * Untuk memformat angka jadi dua digit
     */
    public static String formatDuaDigit(int angka) {
        return angka < 10 ? "0" + angka : String.valueOf(angka);
    }

    /**
     * Untuk mengecek apakah string hanya berisi angka (0-9)
     */
    public static boolean isAngka(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Validasi untuk input String yang tidak boleh kosong
     */
    public static String inputStringWajib(Scanner input, String prompt) {
        String hasil = "";
        while (hasil.isEmpty()) {
            System.out.print(prompt);
            hasil = input.nextLine().trim();
            if (hasil.isEmpty()) {
                System.out.println("❌ Input tidak boleh kosong!");
            }
        }
        return hasil;
    }

    /**
     * Validasi untuk meminta input integer dalam rentang tertentu dari user
     * Validasi: input tidak boleh kosong, harus berupa angka, dan harus dalam rentang min-max
     */
    public static int inputInteger(Scanner input, String prompt, int min, int max) {
        int hasil = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim();

            if (inputStr.isEmpty()) {
                System.out.println("❌ Input tidak boleh kosong!");
                continue;
            }

            if (!isAngka(inputStr)) {
                System.out.println("❌ Input harus berupa angka!");
                continue;
            }

            hasil = Integer.parseInt(inputStr);
            if (hasil < min || hasil > max) {
                System.out.println("❌ Input harus antara " + min + "-" + max + "!");
                continue;
            }
            valid = true;
        }
        return hasil;
    }

    /**
     * Validasi untuk input harga dari user
     * Validasi: input tidak boleh kosong, harus berupa angka valid, dan harus lebih dari 0
     */
    public static double inputHarga(Scanner input, String prompt) {
        double hasil = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim();

            if (inputStr.isEmpty()) {
                System.out.println("❌ Harga tidak boleh kosong!");
                continue;
            }

            if (!isAngka(inputStr)) {
                System.out.println("❌ Harga harus berupa angka!");
                continue;
            }

            hasil = Double.parseDouble(inputStr);
            if (hasil <= 0) {
                System.out.println("❌ Harga harus lebih dari 0!");
                continue;
            }
            valid = true;
        }
        return hasil;
    }

    /**
     * Validasi input konfirmasi Yes/No dari user
     * Input yang valid: 'y' atau 'n' (case-insensitive)
     */
    public static boolean inputYesNo(Scanner input, String prompt) {
        boolean valid = false;
        boolean hasil = false;

        while (!valid) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim().toLowerCase();

            if (inputStr.equals("y")) {
                hasil = true;
                valid = true;
            } else if (inputStr.equals("n")) {
                hasil = false;
                valid = true;
            } else {
                System.out.println("❌ Input tidak valid! Masukkan 'y' atau 'n'");
            }
        }
        return hasil;
    }

    /**
     * Validasi input ID
     * Validasi: input tidak boleh kosong dan harus berupa angka
     */
    public static int inputId(Scanner input, String prompt) {
        int id = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String inputId = input.nextLine().trim();

            if (inputId.isEmpty()) {
                System.out.println("❌ ID tidak boleh kosong!");
                continue;
            }

            if (!isAngka(inputId)) {
                System.out.println("❌ ID harus berupa angka!");
                continue;
            }

            id = Integer.parseInt(inputId);
            valid = true;
        }
        return id;
    }

    /**
     * Validasi input tanggal lengkap
     * Contoh format input: "DD namaBulan YYYY" (contoh: "28 Februari 2025")
     * Validasi:
     *  - Format harus 3 bagian (tanggal, bulan, tahun)
     *  - Tanggal dan tahun harus int
     *  - Nama bulan harus valid (Januari-Desember)
     *  - Tanggal harus sesuai jumlah tanggal di bulan tersebut
     *  - Memperhitungkan tahun kabisat untuk Februari
     */
    public static int[] inputTanggal(Scanner input, String prompt) {
        int tanggal = 0, bulan = 0, tahun = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            String[] waktu = input.nextLine().split(" ");

            if (waktu.length != 3) {
                System.out.println("❌ Format salah! Harus: tanggal bulan tahun");
                continue;
            }

            String strTanggal = waktu[0];
            String namaBulan = waktu[1];
            String strTahun = waktu[2];

            if (!isAngka(strTanggal)) {
                System.out.println("❌ Tanggal harus angka!");
                continue;
            }

            if (!isAngka(strTahun)) {
                System.out.println("❌ Tahun harus angka!");
                continue;
            }

            tanggal = Integer.parseInt(strTanggal);
            tahun = Integer.parseInt(strTahun);
            bulan = getBulanDariNama(namaBulan);

            if (bulan == -1) {
                System.out.println("❌ Nama bulan tidak valid!");
                continue;
            }

            int maxTanggal = 31;
            if (bulan == 2) {
                boolean kabisat = (tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0);
                maxTanggal = kabisat ? 29 : 28;
            } else if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
                maxTanggal = 30;
            }

            if (tanggal < 1 || tanggal > maxTanggal) {
                System.out.println("❌ Tanggal tidak valid untuk bulan " + namaBulan + "!");
                continue;
            }

            valid = true;
        }
        // Return array [hari, bulan, tahun]
        return new int[]{tanggal, bulan, tahun};
    }
}