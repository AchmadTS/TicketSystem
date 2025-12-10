package model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class Pemesanan {
    public int idPemesanan;
    public int idPenerbangan;
    public String namaPelanggan;
    public int jumlah;
    public double totalHarga;
    LocalDateTime waktuPesan;

    public Pemesanan(int idPemesanan, int idPenerbangan, String namaPelanggan, int jumlah, double totalHarga, LocalDateTime waktuPesan) {
        this.idPemesanan = idPemesanan;
        this.idPenerbangan = idPenerbangan;
        this.namaPelanggan = namaPelanggan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.waktuPesan = waktuPesan;
    }

    public String getNamaBulan(int bulan) {
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

    public String ringkasan(Penerbangan penerbangan) {
        String infoPenerbangan = "";
        if (penerbangan != null) {
            String namaBulan = getNamaBulan(penerbangan.bulan);
            infoPenerbangan = penerbangan.pesawat + " " + penerbangan.asal + " -> " + penerbangan.tujuan + " (" + penerbangan.hari + " " + namaBulan + " " + penerbangan.tahun + ")";
        } else {
            infoPenerbangan = "Penerbangan sudah dihapus";
        }

        String waktu = waktuPesan.getYear() + "-" + (waktuPesan.getMonthValue() < 10 ? "0" : "") + waktuPesan.getMonthValue() + "-" + (waktuPesan.getDayOfMonth() < 10 ? "0" : "") + waktuPesan.getDayOfMonth() + " " + (waktuPesan.getHour() < 10 ? "0" : "") + waktuPesan.getHour() + ":" + (waktuPesan.getMinute() < 10 ? "0" : "") + waktuPesan.getMinute();
        DecimalFormat df = new DecimalFormat("#,###");
        return "ID Pesanan #" + idPemesanan
                + " | Nama: " + namaPelanggan
                + " | " + infoPenerbangan
                + " | Jumlah: " + jumlah
                + " | Total: Rp" + df.format(totalHarga)
                + " | Waktu Pesan: " + waktu;
    }
}
