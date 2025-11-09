package controller;

import model.Penerbangan;
import model.Pemesanan;
import view.Tampilan;
import java.util.Scanner;

public class SistemTiket {
    public Scanner input = new Scanner(System.in);
    public Tampilan view = new Tampilan();
    public Penerbangan[] daftarPenerbangan = new Penerbangan[100];
    public Pemesanan[] daftarPemesanan = new Pemesanan[100];
    public int jumlahPenerbangan = 0;
    public int jumlahPemesanan = 0;
    public int nextIdPenerbangan = 1;
    public int nextIdPemesanan = 1;

    public void isiContohPenerbangan() {
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Garuda 101", "Jakarta", "Surabaya", 750000, 11, 10, 2025, 100);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Lion Air 202", "Jakarta", "Bali", 650000, 11, 12, 2025, 120);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Batik 303", "Bandung", "Jakarta", 300000, 11, 9, 2025, 80);
    }

    public void mainMenu() {
        while (true) {
            view.menuUtama();
            String pilih = input.nextLine();

            switch (pilih) {
                case "1" -> urutkanPenerbanganSementara();
                case "2" -> cariPenerbangan();
                case "3" -> new TambahPenerbangan(this).jalankan();
                case "4" -> new EditPenerbangan(this).jalankan();
                case "5" -> new HapusPenerbangan(this).jalankan();
                case "6" -> new PesanTiket(this).jalankan();
                case "7" -> view.showDaftarPemesanan(daftarPemesanan, daftarPenerbangan, jumlahPemesanan, jumlahPenerbangan);
                case "0" -> {
                    System.out.println("Terima kasih 🙏🏻");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void urutkanPenerbanganSementara() {
        Penerbangan[] salinan = new Penerbangan[jumlahPenerbangan];
        for (int i = 0; i < jumlahPenerbangan; i++) {
            salinan[i] = daftarPenerbangan[i];
        }

        for (int i = 0; i < jumlahPenerbangan - 1; i++) {
            for (int j = 0; j < jumlahPenerbangan - i - 1; j++) {
                Penerbangan a = salinan[j];
                Penerbangan b = salinan[j + 1];
                if (a.tahun > b.tahun || (a.tahun == b.tahun && a.bulan > b.bulan) ||
                        (a.tahun == b.tahun && a.bulan == b.bulan && a.hari > b.hari)) {
                    salinan[j] = b;
                    salinan[j + 1] = a;
                }
            }
        }
        view.showDaftarPenerbangan(salinan, jumlahPenerbangan);
    }

    public void cariPenerbangan() {
        System.out.print("Masukkan asal: ");
        String asal = input.nextLine().trim();
        System.out.print("Masukkan tujuan: ");
        String tujuan = input.nextLine().trim();
        System.out.print("Masukkan waktu (tanggal bulan tahun): ");
        String waktu = input.nextLine().trim();

        String[] bagian = waktu.split(" ");
        if (bagian.length < 3) {
            System.out.println("❌ Format waktu salah! Contoh: 09 November 2025");
            return;
        }

        int hari = Integer.parseInt(bagian[0]);
        String namaBulan = bagian[1];
        int tahun = Integer.parseInt(bagian[2]);

        int bulan = switch (namaBulan.toLowerCase()) {
            case "januari" -> 1;
            case "februari" -> 2;
            case "maret" -> 3;
            case "april" -> 4;
            case "mei" -> 5;
            case "juni" -> 6;
            case "juli" -> 7;
            case "agustus" -> 8;
            case "september" -> 9;
            case "oktober" -> 10;
            case "november" -> 11;
            case "desember" -> 12;
            default -> -1;
        };

        if (bulan == -1) {
            System.out.println("❌ Nama bulan tidak dikenali.");
            return;
        }

        boolean ketemu = false;
        for (int i = 0; i < jumlahPenerbangan; i++) {
            Penerbangan p = daftarPenerbangan[i];
            if (p.asal.equalsIgnoreCase(asal) && p.tujuan.equalsIgnoreCase(tujuan)
                    && p.hari == hari && p.bulan == bulan && p.tahun == tahun) {
                if (!ketemu) System.out.println("\nHasil pencarian:");
                System.out.println(p);
                ketemu = true;
            }
        }
        if (!ketemu) System.out.println("❌ Tidak ditemukan.");
    }

    public Penerbangan cariById(int id) {
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i].id == id) return daftarPenerbangan[i];
        }
        return null;
    }
}