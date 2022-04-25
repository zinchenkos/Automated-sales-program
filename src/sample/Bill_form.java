package sample;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.FontMetrics;

import static sample.Listfortovar.product;


public class Bill_form extends javax.swing.JFrame {


    public PageFormat getPageFormat(PrinterJob pj)
    {
        System.out.println("fsdd12231ffs");

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }






    public class BillPrintable implements Printable {
        public double checkAll(){

            double c = 0;
            for (int i = 0; i < product.size(); i++) {
                c=c+product.get(i).getPrice();
            }
            return c;
        }




        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)
                throws PrinterException
        {
            System.out.println("fsd232323dffs");
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;
                System.out.println("1fsd23232323dffs");

                double width = pageFormat.getImageableWidth();

                System.out.println("2fsd23232323dffs");

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());

                ////////// code by alqama//////////////

                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                //    int idLength=metrics.stringWidth("000000");
                //int idLength=metrics.stringWidth("00");
                int idLength=metrics.stringWidth("000");
                int amtLength=metrics.stringWidth("000000");
                int qtyLength=metrics.stringWidth("00000");
                int priceLength=metrics.stringWidth("000000");
                int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

                System.out.println("3fsd23232323dffs");
                //    int idPosition=0;
                //    int productPosition=idPosition + idLength + 2;
                //    int pricePosition=productPosition + prodLength +10;
                //    int qtyPosition=pricePosition + priceLength + 2;
                //    int amtPosition=qtyPosition + qtyLength + 2;

                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;


                System.out.println("fsd23232323dffs");
                try{
                    /*Draw Header*/
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    int headerRectHeighta=40;


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("          Store Bill Receipt         ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Product Name              T.Price   ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                    for (int i = 0; i < product.size(); i++){
                        char[] chars = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,};
                        String str = new String(chars, 0, 28-product.get(i).getName().length());
                        g2d.drawString(" "+product.get(i).getName()+str+product.get(i).getPrice()+"  ",10,y);y+=yShift;
                    }
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Total amount: "+checkAll()+"               ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("            BoyOrelStudio            ",10,y);y+=yShift;
                    g2d.drawString("              0668912465             ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("        It's revolution, Johny!      ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;


                    System.out.println("fsdd23234343ffs");


//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift;


                }
                catch(Exception r){
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }



    public void startPrint() {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("fsdd1212323ffs");
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        System.out.println("fsdd345345435ffs");
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


}