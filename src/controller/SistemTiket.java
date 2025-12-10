package controller;

import controller.create.PesanTiket;
import controller.create.TambahPenerbangan;
import controller.delete.HapusPenerbangan;
import controller.updade.EditPenerbangan;
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
                if (p1.tahun > p2.tahun) {
                    tukar = true;
                } else if (p1.tahun == p2.tahun) {
                    if (p1.bulan > p2.bulan) {
                        tukar = true;
                    } else if (p1.bulan == p2.bulan) {
                        if (p1.hari > p2.hari) {
                            tukar = true;
                        } else if (p1.hari == p2.hari) {
                            if (p1.jam > p2.jam) {
                                tukar = true;
                            } else if (p1.jam == p2.jam) {
                                if (p1.menit > p2.menit) {
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

    public void cariPenerbangan() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.println("║                 🔍 CARI PENERBANGAN                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        String asal = "";
        while (asal.isEmpty()) {
            System.out.print("Masukkan asal: ");
            asal = input.nextLine().trim();
            if (asal.isEmpty()) {
                System.out.println("❌ Kota asal tidak boleh kosong!");
            }
        }

        String tujuan = "";
        while (tujuan.isEmpty()) {
            System.out.print("Masukkan tujuan: ");
            tujuan = input.nextLine().trim();
            if (tujuan.isEmpty()) {
                System.out.println("❌ Kota tujuan tidak boleh kosong!");
            }
        }

        int hari = 0, bulan = 0, tahun = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Masukkan waktu keberangkatan (contoh: 09 September 2025): ");
            String waktu = input.nextLine().trim();

            String[] bagian = waktu.split(" ");
            if (bagian.length != 3) {
                System.out.println("❌ Format salah! Contoh: 09 September 2025");
                continue;
            }

            String strHari = bagian[0];
            String namaBulan = bagian[1].toLowerCase();
            String strTahun = bagian[2];

            boolean hariValid = true;
            for (int i = 0; i < strHari.length(); i++) {
                if (strHari.charAt(i) < '0' || strHari.charAt(i) > '9') {
                    System.out.println("❌ Tanggal harus berupa angka!");
                    hariValid = false;
                    break;
                }
            }
            if (!hariValid) continue;

            boolean tahunValid = true;
            for (int i = 0; i < strTahun.length(); i++) {
                if (strTahun.charAt(i) < '0' || strTahun.charAt(i) > '9') {
                    System.out.println("❌ Tahun harus berupa angka!");
                    tahunValid = false;
                    break;
                }
            }
            if (!tahunValid) continue;

            hari = Integer.parseInt(strHari);
            tahun = Integer.parseInt(strTahun);

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
                    System.out.println("❌ Nama bulan tidak valid!");
                    bulan = -1;
            }

            if (bulan == -1) continue;
            int maxHari = 31;
            if (bulan == 2) {
                boolean kabisat = (tahun % 4 == 0 && tahun % 100 != 0) || (tahun % 400 == 0);
                maxHari = kabisat ? 29 : 28;
            } else if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
                maxHari = 30;
            }

            if (hari < 1 || hari > maxHari) {
                System.out.println("❌ Tanggal tidak valid untuk bulan yang dipilih!");
                continue;
            }
            valid = true;
        }

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

    public Penerbangan cariById(int id) {
        for (int i = 0; i < jumlahPenerbangan; i++) {
            if (daftarPenerbangan[i] != null && daftarPenerbangan[i].id == id) {
                return daftarPenerbangan[i];
            }
        }
        return null;
    }
}