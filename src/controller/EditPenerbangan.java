package controller;

import model.Penerbangan;

public class EditPenerbangan {
    private SistemTiket sistem;

    public EditPenerbangan(SistemTiket sistem) {
        this.sistem = sistem;
    }

    public void run() {
        System.out.print("Masukkan ID penerbangan yang akan diedit: ");
        int id = Integer.parseInt(sistem.input.nextLine());
        Penerbangan p = sistem.cariById(id);
        if (p == null) {
            System.out.println("❌ Tidak ditemukan.");
            return;
        }
        System.out.println("Data lama: " + p);
        System.out.print("Harga baru (kosong = tetap): ");
        String harga = sistem.input.nextLine();
        if (!harga.isEmpty()) p.harga = Double.parseDouble(harga);
        System.out.println("✅ Data diperbarui.");
    }
}
