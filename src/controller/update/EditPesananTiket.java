package controller.update;

import controller.SistemTiket;
import model.*;
import util.Helper;
import view.EditPesananView;

public class EditPesananTiket {
    private SistemTiket sistem;
    private EditPesananView view;

    public EditPesananTiket(SistemTiket sistem) {
        this.sistem = sistem;
        this.view = new EditPesananView();
    }

    public void run() {
        view.showHeader();
        if (sistem.jumlahPemesanan == 0) {
            view.showTidakAdaPemesanan();
            return;
        }

        sistem.view.showDaftarPemesanan(sistem.daftarPemesanan, sistem.daftarPenerbangan, sistem.jumlahPemesanan, sistem.jumlahPenerbangan);
        System.out.println();

        // Cari pemesanan berdasarkan ID
        Pemesanan pemesanan = null;
        while (pemesanan == null) {
            int idPemesanan = Helper.inputId(sistem.input, "Masukkan ID pemesanan yang akan diedit: ");
            for (int i = 0; i < sistem.jumlahPemesanan; i++) {
                if (sistem.daftarPemesanan[i].idPemesanan == idPemesanan) {
                    pemesanan = sistem.daftarPemesanan[i];
                    break;
                }
            }

            if (pemesanan == null) {
                System.out.println("❌ ID pemesanan tidak ditemukan! Silakan coba lagi");
            }
        }

        // Ambil data penerbangan lama
        Penerbangan penerbanganLama = sistem.cariById(pemesanan.idPenerbangan);
        if (penerbanganLama == null) {
            System.out.println("❌ Data penerbangan tidak ditemukan!");
            return;
        }

        view.showDataPemesananSaatIni(pemesanan, penerbanganLama);
        System.out.print("Nama pemesan baru [" + pemesanan.namaPelanggan + "]: ");
        String namaBaru = sistem.input.nextLine().trim();
        if (!namaBaru.isEmpty()) {
            pemesanan.namaPelanggan = namaBaru;
        }

        // Ganti penerbangan (opsional)
        boolean gantiPenerbangan = Helper.inputYesNo(sistem.input, "Ganti penerbangan? (y/n): ");
        Penerbangan penerbanganBaru = penerbanganLama;
        if (gantiPenerbangan) {
            view.showHeaderDaftarPenerbangan();
            sistem.view.showDaftarPenerbangan(sistem.daftarPenerbangan, sistem.jumlahPenerbangan);
            System.out.println();

            Penerbangan p = null;
            while (p == null) {
                int idPenerbanganBaru = Helper.inputId(sistem.input, "Masukkan ID penerbangan baru: ");
                p = sistem.cariById(idPenerbanganBaru);
                if (p == null) {
                    System.out.println("❌ ID penerbangan tidak ditemukan! Silakan coba lagi");
                }
            }

            // Validasi sisa kursi
            if (pemesanan.jumlah > p.jumlahKursi) {
                view.showKursiTidakCukup(p.jumlahKursi);
            } else {
                // Balikin kursi ke penerbangan lama
                penerbanganLama.jumlahKursi += pemesanan.jumlah;
                view.showKursiDikembalikan(pemesanan.jumlah);

                // Kurangi kursi di penerbangan baru
                p.jumlahKursi -= pemesanan.jumlah;
                view.showKursiDipesan(pemesanan.jumlah);

                // Update data pemesanan tiket
                pemesanan.idPenerbangan = p.id;
                pemesanan.totalHarga = pemesanan.jumlah * p.harga;
                penerbanganBaru = p;
                view.showPenerbanganBerhasilDiganti();
            }
        }

        // Edit jumlah tiket (opsional)
        boolean editJumlah = Helper.inputYesNo(sistem.input, "Edit jumlah tiket? (y/n): ");
        if (editJumlah) {
            boolean jumlahValid = false;
            while (!jumlahValid) {
                int jumlahBaru = Helper.inputInteger(sistem.input, "Masukkan jumlah tiket baru: ", 1, Integer.MAX_VALUE);
                int selisih = jumlahBaru - pemesanan.jumlah;
                if (selisih > 0) {
                    // Jumlah tiket bertambah, cek ketersediaan kursi
                    if (selisih > penerbanganBaru.jumlahKursi) {
                        view.showKursiTidakCukupEdit(penerbanganBaru.jumlahKursi);
                    } else {
                        penerbanganBaru.jumlahKursi -= selisih;
                        pemesanan.jumlah = jumlahBaru;
                        pemesanan.totalHarga = pemesanan.jumlah * penerbanganBaru.harga;
                        view.showJumlahTiketBerkurang(selisih);
                        jumlahValid = true;
                    }
                } else if (selisih < 0) {
                    // Jumlah tiket berkurang, kembalikan kursi
                    penerbanganBaru.jumlahKursi += Math.abs(selisih);
                    pemesanan.jumlah = jumlahBaru;
                    pemesanan.totalHarga = pemesanan.jumlah * penerbanganBaru.harga;
                    view.showJumlahTiketBertambah(Math.abs(selisih));
                    jumlahValid = true;
                } else {
                    view.showJumlahTidakBerubah();
                    jumlahValid = true;
                }
            }
        }
        view.showKonfirmasiUpdate(pemesanan, penerbanganBaru);
    }
}