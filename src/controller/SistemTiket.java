package controller;

import java.time.LocalDateTime;
import java.util.Scanner;

import model.Penerbangan;
import model.Pemesanan;
import view.Tampilan;

public class SistemTiket {
    private Scanner input = new Scanner(System.in);
    private Tampilan view = new Tampilan();
    private Penerbangan[] daftarPenerbangan = new Penerbangan[100];
    private Pemesanan[] daftarPemesanan = new Pemesanan[100];
    private int jumlahPenerbangan = 0;
    private int jumlahPemesanan = 0;
    private int nextIdPenerbangan = 1;
    private int nextIdPemesanan = 1;

    public void isiContohPenerbangan() {
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Garuda 101", "Jakarta", "Surabaya", 750000, 11, 10, 2025, 100);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Lion Air 202", "Jakarta", "Bali", 650000, 11, 12, 2025, 120);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Batik 303", "Bandung", "Jakarta", 300000, 11, 9, 2025, 80);
    }

    public void mulai() {
        while (true) {
            view.menuUtama();
            String pilih = input.nextLine();

            switch (pilih) {
                case "1" -> {
                    urutkanPenerbanganSementara();
                }
                case "2" -> cariPenerbangan();
                case "3" -> tambahPenerbangan();
                case "4" -> editPenerbangan();
                case "5" -> hapusPenerbangan();
                case "6" -> pesanTiket();
                case "7" ->
                        view.showDaftarPemesanan(daftarPemesanan, daftarPenerbangan, jumlahPemesanan, jumlahPenerbangan);
                case "0" -> {
                    System.out.println("Terima kasih 🙏🏻");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void urutkanPenerbanganSementara() {
        Penerbangan[] salinan = new Penerbangan[jumlahPenerbangan];
        for (int i = 0; i < jumlahPenerbangan; i++) {
            salinan[i] = daftarPenerbangan[i];
        }

        for (int i = 0; i < jumlahPenerbangan - 1; i++) {
            for (int j = 0; j < jumlahPenerbangan - i - 1; j++) {
                Penerbangan a = salinan[j];
                Penerbangan b = salinan[j + 1];
                if (a.tahun > b.tahun || (a.tahun == b.tahun && a.bulan > b.bulan) || (a.tahun == b.tahun && a.bulan == b.bulan && a.hari > b.hari)) {
                    salinan[j] = b;
                    salinan[j + 1] = a;
                }
            }
        }
        view.showDaftarPenerbangan(salinan, jumlahPenerbangan);
    }

    private void tambahPenerbangan() {
        System.out.print("Nama pesawat: ");
        String pesawat = input.nextLine();
        System.out.print("Asal: ");
        String asal = input.nextLine();
        System.out.print("Tujuan: ");
        String tujuan = input.nextLine();
        System.out.print("Harga: ");
        double harga = Double.parseDouble(input.nextLine());
        System.out.print("Tanggal (hari): ");
        int hari = Integer.parseInt(input.nextLine());
        System.out.print("Bulan (angka): ");
        int bulan = Integer.parseInt(input.nextLine());
        System.out.print("Tahun: ");
        int tahun = Integer.parseInt(input.nextLine());
        System.out.print("Jumlah kursi: ");
        int kursi = Integer.parseInt(input.nextLine());

        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, pesawat, asal, tujuan, harga, bulan, hari, tahun, kursi);
        System.out.println("✅ Penerbangan ditambahkan.");
    }

    private void cariPenerbangan() {
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
            if (p.asal.equalsIgnoreCase(asal) && p.tujuan.equalsIgnoreCase(tujuan) && p.hari == hari && p.bulan == bulan && p.tahun == tahun) {
                if (!ketemu) System.out.println("\nHasil pencarian:");
                System.out.println(p);
                ketemu = true;
            }
        }
        if (!ketemu) System.out.println("❌ Tidak ditemukan.");
    }

    private void editPenerbangan() {
        System.out.print("Masukkan ID penerbangan yang akan diedit: ");
        int id = Integer.parseInt(input.nextLine());
        Penerbangan p = cariById(id);
        if (p == null) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }
        System.out.println("Data lama: " + p);
        System.out.print("Harga baru (kosong = tetap): ");
        String harga = input.nextLine();
        if (!harga.isEmpty()) p.harga = Double.parseDouble(harga);
        System.out.println("✅ Data diperbarui.");
    }

    private void hapusPenerbangan() {
        System.out.print("Masukkan ID penerbangan: ");
        int id = Integer.parseInt(input.nextLine());
        int idx = -1;
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i].id == id) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }
        for (int i = idx; i < jumlahPenerbangan - 1; i++)
            daftarPenerbangan[i] = daftarPenerbangan[i + 1];
        jumlahPenerbangan--;
        System.out.println("🗑️  Dihapus.");
    }

    private void pesanTiket() {
        view.showDaftarPenerbangan(daftarPenerbangan, jumlahPenerbangan);
        System.out.print("\nMasukkan ID penerbangan: ");
        int id = Integer.parseInt(input.nextLine());
        Penerbangan p = cariById(id);
        if (p == null) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }

        System.out.print("Nama pemesan: ");
        String nama = input.nextLine();
        System.out.print("Jumlah tiket: ");
        int jumlah = Integer.parseInt(input.nextLine());

        if (jumlah > p.jumlahKursi) {
            System.out.println("❌ Kursi tidak cukup!");
            return;
        }

        p.jumlahKursi -= jumlah;
        double total = jumlah * p.harga;
        daftarPemesanan[jumlahPemesanan++] =
                new Pemesanan(nextIdPemesanan++, p.id, nama, jumlah, total, LocalDateTime.now());
        System.out.printf("✅ Pemesanan berhasil! Total: Rp%,.0f\n", total);
    }

    private Penerbangan cariById(int id) {
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i].id == id) return daftarPenerbangan[i];
        }
        return null;
    }
}
