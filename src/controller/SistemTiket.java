package controller;

import controller.create.*;
import controller.update.*;
import controller.delete.*;
import model.*;
import view.MenuView;
import util.Helper;
import java.util.Scanner;

public class SistemTiket {
    public Scanner input = new Scanner(System.in);
    public MenuView view = new MenuView();
    public Penerbangan[] daftarPenerbangan = new Penerbangan[100];
    public Pemesanan[] daftarPemesanan = new Pemesanan[100];
    public int jumlahPenerbangan = 0;
    public int jumlahPemesanan = 0;
    public int nextIdPenerbangan = 1;
    public int nextIdPemesanan = 1;

    // Data dummy
    public void isiContohPenerbangan() {
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Garuda 101", "Jakarta", "Surabaya", 750000, 10, 10, 2025, 8, 30, 100);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Lion Air 202", "Jakarta", "Bali", 650000, 11, 12, 2025, 14, 45, 120);
        daftarPenerbangan[jumlahPenerbangan++] = new Penerbangan(nextIdPenerbangan++, "Batik 303", "Bandung", "Jakarta", 300000, 9, 9, 2025, 6, 15, 80);
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
                    EditPesananTiket editPemesanan = new EditPesananTiket(this);
                    editPemesanan.run();
                    break;
                case "8":
                    HapusPesananTiket hapusPemesanan = new HapusPesananTiket(this);
                    hapusPemesanan.run();
                    break;
                case "9":
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

    // Menampilkan daftar penerbangan yang terurut berdasarkan waktu keberangkatan
    // Sorting awal ke akhir (ascending)
    // Urutan: tahun → bulan → hari → jam → menit
    // Algoritma Bubble Sort
    public void urutanPenerbanganSementara() {
        if (jumlahPenerbangan == 0) {
            System.out.println("\n⚠️ Belum ada data penerbangan.");
            return;
        }

        Penerbangan[] salinan = new Penerbangan[jumlahPenerbangan];
        for (int i = 0; i < jumlahPenerbangan; i++) {
            salinan[i] = daftarPenerbangan[i];
        }

        for (int i = 0; i < jumlahPenerbangan - 1; i++) {
            for (int j = i + 1; j < jumlahPenerbangan; j++) {
                Penerbangan p1 = salinan[i];
                Penerbangan p2 = salinan[j];
                boolean tukar = false;
                if (p1.tahun > p2.tahun) { // bandingin tahun
                    tukar = true;
                } else if (p1.tahun == p2.tahun) {
                    if (p1.bulan > p2.bulan) { // bandingin bulan
                        tukar = true;
                    } else if (p1.bulan == p2.bulan) {
                        if (p1.hari > p2.hari) { // bandingin hari
                            tukar = true;
                        } else if (p1.hari == p2.hari) {
                            if (p1.jam > p2.jam) { // bandingin jam
                                tukar = true;
                            } else if (p1.jam == p2.jam) {
                                if (p1.menit > p2.menit) { // bandingin menit
                                    tukar = true;
                                }
                            }
                        }
                    }
                }
                if (tukar) {
                    Penerbangan temp = salinan[i];
                    salinan[i] = salinan[j];
                    salinan[j] = temp;
                }
            }
        }
        view.showDaftarPenerbangan(salinan, jumlahPenerbangan);
    }

    // Mencari penerbangan berdasarkan: Kota asal, Kota tujuan, waktu keberangkatan
    // Pencarian case-insensitive untuk asal dan tujuan
    public void cariPenerbangan() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 🔍 CARI PENERBANGAN                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        String asal = Helper.inputStringWajib(input, "Masukkan asal: ");
        String tujuan = Helper.inputStringWajib(input, "Masukkan tujuan: ");

        int[] tanggal = Helper.inputTanggal(input, "Masukkan waktu keberangkatan (contoh: 09 September 2025): ");
        int hari = tanggal[0];
        int bulan = tanggal[1];
        int tahun = tanggal[2];

        boolean ketemu = false;
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│                 🔍 HASIL PENCARIAN                       │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        for (int i = 0; i < jumlahPenerbangan; i++) {
            Penerbangan p = daftarPenerbangan[i];
            if (p.asal.equalsIgnoreCase(asal) && p.tujuan.equalsIgnoreCase(tujuan) && p.hari == hari && p.bulan == bulan && p.tahun == tahun) {
                System.out.println(p);
                ketemu = true;
            }
        }
        if (!ketemu) {
            System.out.println("❌ Tidak ditemukan penerbangan dengan kriteria tersebut.");
        }
    }

    // Cari penerbangan berdasarkan ID
    public Penerbangan cariById(int id) {
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i] != null && daftarPenerbangan[i].id == id) {
                return daftarPenerbangan[i];
            }
        }
        return null;
    }
}