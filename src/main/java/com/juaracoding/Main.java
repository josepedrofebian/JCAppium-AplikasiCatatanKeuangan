package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main {
    static DesiredCapabilities dc;
    static URL url;
    static AndroidDriver driver;

    static void buttonAddClick() {
        MobileElement btnAdd = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/fabMenu"));
        btnAdd.click();
    }

    static void buttonPemasukanClick() {
        MobileElement btnPemasukan = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/btnIncome"));
        btnPemasukan.click();
    }

    static void inputJumlah(String jumlah) {
        MobileElement fieldJumlah = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/etAmount"));
        fieldJumlah.sendKeys(jumlah);
    }

    static void inputKeterangan(String keterangan) {
        MobileElement fieldKeterangan = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/etNote"));
        fieldKeterangan.sendKeys(keterangan);
    }

    static void buttonSimpanClick() {
        MobileElement btnSimpan = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/btSave"));
        btnSimpan.click();
    }

    static String txtSuccessPemasukan(){
        MobileElement resultPemasukan = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]"));
        String resPemasukan = resultPemasukan.getText();
        return resPemasukan;
    }

    static String txtSuccessPengeluaran(){
        MobileElement resultPengeluaran = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup[2]/android.widget.RelativeLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]"));
        String resPengeluaran = resultPengeluaran.getText();
        return resPengeluaran;
    }

    static String txtSaldo(){
        MobileElement resultSaldo = (MobileElement) driver.findElement(By.id("com.chad.financialrecord:id/tvBalance"));
        String resSaldo = resultSaldo.getText();
        return resSaldo;
    }

    public static void main(String[] args) throws MalformedURLException {
        dc = new DesiredCapabilities();
        dc.setCapability("deviceName", "Pixel 2 API 25");
        dc.setCapability("udid", "emulator-5554");
        dc.setCapability("platformName", "android");
        dc.setCapability("appPackage", "com.chad.financialrecord");
        dc.setCapability("appActivity", "com.rookie.catatankeuangan.feature.splash.SplashActivity");
        dc.setCapability("noReset", false);
        url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Appium Start");

        //aksi untuk pemasukan
        buttonAddClick();
        buttonPemasukanClick();
        inputJumlah("1000000");
        inputKeterangan("");
        buttonSimpanClick();

        //assert pemasukan
        System.out.println("Test Pemasukan : ");
        if (txtSuccessPemasukan().contains("1.000.000")) {
            System.out.println("Status : PASS");
        } else {
            System.out.println("Status : FAIL");
        }

        //aksi untuk pengeluaran
        buttonAddClick();
        inputJumlah("500000");
        inputKeterangan("");
        buttonSimpanClick();

        //assert pengeluaran
        System.out.println("Test Pengeluaran : ");
        if (txtSuccessPengeluaran().contains("500.000")) {
            System.out.println("Status : PASS");
        } else {
            System.out.println("Status : FAIL");
        }

        //assert saldo
        System.out.println("Test Saldo : ");
        if (txtSaldo().contains("500.000")) {
            System.out.println("Status : PASS");
        } else {
            System.out.println("Status : FAIL");
        }
    }
}