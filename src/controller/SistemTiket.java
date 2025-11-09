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
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Garuda 101", "Jakarta", "Surabaya", 750000, 10, 10, 2025, 100);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Lion Air 202", "Jakarta", "Bali", 650000, 11, 12, 2025, 120);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Batik 303", "Bandung", "Jakarta", 300000, 9, 9, 2025, 80);
    }

    public void mainMenu() {
        while (true) {
            view.menuUtama();
            String pilih = input.nextLine();

            switch (pilih) {
                case "1":
                    urutanPenerbanganSementara();
                    break;
                case "2":
                    cariPenerbangan();
                    break;
                case "3":
                    TambahPenerbangan tambah = new TambahPenerbangan(this);
                    tambah.run();
                    break;
                case "4":
                    EditPenerbangan edit = new EditPenerbangan(this);
                    edit.run();
                    break;
                case "5":
                    HapusPenerbangan hapus = new HapusPenerbangan(this);
                    hapus.run();
                    break;
                case "6":
                    PesanTiket pesan = new PesanTiket(this);
                    pesan.run();
                    break;
                case "7":
                    view.showDaftarPemesanan(daftarPemesanan, daftarPenerbangan, jumlahPemesanan, jumlahPenerbangan);
                    break;
                case "0":
                    System.out.println("Terima kasih 🙏🏻");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    public void urutanPenerbanganSementara() {
        Penerbangan[] salinan = new Penerbangan[jumlahPenerbangan];
        for (int i = 0; i < jumlahPenerbangan; i++) {
            salinan[i] = daftarPenerbangan[i];
        }

        for (int i = 0; i < jumlahPenerbangan - 1; i++) {
            for (int j = i + 1; j < jumlahPenerbangan; j++) {
                Penerbangan p1 = salinan[i];
                Penerbangan p2 = salinan[j];
                if (p1.tahun > p2.tahun ||
                        (p1.tahun == p2.tahun && p1.bulan > p2.bulan) ||
                        (p1.tahun == p2.tahun && p1.bulan == p2.bulan && p1.hari > p2.hari)) {
                    Penerbangan temp = salinan[i];
                    salinan[i] = salinan[j];
                    salinan[j] = temp;
                }
            }
        }
        view.showDaftarPenerbangan(salinan, jumlahPenerbangan);
    }

    public void cariPenerbangan() {
        System.out.print("Masukkan asal: ");
        String asal = input.nextLine();

        System.out.print("Masukkan tujuan: ");
        String tujuan = input.nextLine();

        System.out.print("Masukkan waktu keberangkatan: (contoh: 09 September 2025): ");
        String waktu = input.nextLine();

        String[] bagian = waktu.split(" ");
        if (bagian.length != 3) {
            System.out.println("Format salah! Contoh: 09 September 2025");
            return;
        }

        int hari = Integer.parseInt(bagian[0]);
        String namaBulan = bagian[1].toLowerCase();
        int tahun = Integer.parseInt(bagian[2]);
        int bulan;

        switch (namaBulan) {
            case "januari":
                bulan = 1;
                break;
            case "februari":
                bulan = 2;
                break;
            case "maret":
                bulan = 3;
                break;
            case "april":
                bulan = 4;
                break;
            case "mei":
                bulan = 5;
                break;
            case "juni":
                bulan = 6;
                break;
            case "juli":
                bulan = 7;
                break;
            case "agustus":
                bulan = 8;
                break;
            case "september":
                bulan = 9;
                break;
            case "oktober":
                bulan = 10;
                break;
            case "november":
                bulan = 11;
                break;
            case "desember":
                bulan = 12;
                break;
            default:
                bulan = -1;
                break;
        }

        if (bulan == -1) {
            System.out.println("Bulan tidak valid!");
            return;
        }

        boolean ketemu = false;
        for (int i = 0; i < jumlahPenerbangan; i++) {
            Penerbangan p = daftarPenerbangan[i];
            if (p.asal.equalsIgnoreCase(asal) &&
                    p.tujuan.equalsIgnoreCase(tujuan) &&
                    p.hari == hari && p.bulan == bulan && p.tahun == tahun) {

                if (!ketemu) {
                    System.out.println();
                    System.out.println("Hasil pencarian:");
                }

                System.out.println(p);
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("❌ Tidak ditemukan.");
        }
    }

    public Penerbangan cariById(int id) {
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i] != null && daftarPenerbangan[i].id == id) {
                return daftarPenerbangan[i];
            }
        }
        return null;
    }
}