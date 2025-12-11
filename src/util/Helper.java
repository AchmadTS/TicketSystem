package util;

public class Helper {
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

    public static String formatDuaDigit(int angka) {
        return angka < 10 ? "0" + angka : String.valueOf(angka);
    }
}