/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiviatt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author riedinma
 */
public class Kiviatt extends JComponent implements TableModelListener {

    private TableModel tableModel;
    private List<KiviattAxe> listAxes;
    private KiviattAxe selectedAxe = null;
    private final int SIZE = 800;
    private final int SIZE_BORDURE_EXT = 70;
    private final int SIZE_BORDURE_INT = 30;
    private final int SIZE_DECALAGE_LABEL = 40;
    private final int SIZE_DECALAGE_V = 5;
    private final int SIZE_POINT = 5;
    private final int SIZE_DRAG = SIZE_POINT + 5;

    public Kiviatt() {
        this(null);
    }

    Kiviatt(TableModel tableModel) {
        listAxes = new ArrayList<>();
        setModel(tableModel);
        setSize(SIZE, SIZE);
        setPreferredSize(new Dimension(SIZE, SIZE));
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean b = true;
                for (KiviattAxe axe : listAxes) {
                    if (Point.distance((double) e.getX(), (double) e.getY(), (double) axe.getxValeur(), (double) axe.getyValeur()) < SIZE_DRAG) {
                        System.out.println("Axe " + axe.getNom());
                        selectedAxe = axe;
                        b = false;
                    }
                }
                if (b) {
                    selectedAxe = null;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedAxe != null) {
                    tableModel.setValueAt(selectedAxe.getNewValueFromXY(e.getX(), e.getY()), selectedAxe.getIndex(), 1);
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedAxe != null) {
                    tableModel.setValueAt(selectedAxe.getNewValueFromXY(e.getX(), e.getY()), selectedAxe.getIndex(), 1);
                }
            }

        });
    }

    public void setModel(TableModel aTableModel) {
        this.tableModel = aTableModel;
        setListFromModel();
        aTableModel.addTableModelListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);
        dessinerTraitsEtSurfaces(g);
        for (KiviattAxe axe : listAxes) {
            dessinerAxe(g, axe);
        }

    }

    private void calculsAxe(KiviattAxe axe, double angle) {
        int xCentre = SIZE / 2;
        int yCentre = SIZE / 2;

        int rayon = SIZE / 2 - SIZE_BORDURE_EXT;

        int xDepart = xCentre + (int) (SIZE_BORDURE_INT * Math.cos(angle));
        int yDepart = xCentre + (int) (SIZE_BORDURE_INT * Math.sin(angle));

        double x = rayon * Math.cos(angle);
        double y = rayon * Math.sin(angle);

        int xArrivee = (int) x + xDepart;
        int yArrivee = (int) y + yDepart;

        int xLabel = xArrivee + (int) (SIZE_DECALAGE_LABEL * Math.cos(angle));
        int yLabel = yArrivee + (int) (SIZE_DECALAGE_LABEL * Math.sin(angle));

        int xLabelvMin = xDepart - (int) (SIZE_DECALAGE_V * Math.cos(angle));
        int yLabelvMin = yDepart - (int) (SIZE_DECALAGE_V * Math.sin(angle));

        int xLabelvMax = xArrivee + (int) (SIZE_DECALAGE_V * Math.cos(angle));
        int yLabelvMax = yArrivee + (int) (SIZE_DECALAGE_V * Math.sin(angle));

        double t = axe.getUniformValue();
        double rValeur = t * rayon;

        int xValeur = xCentre + (int) ((rValeur + SIZE_BORDURE_INT) * Math.cos(angle));
        int yValeur = yCentre + (int) ((rValeur + SIZE_BORDURE_INT) * Math.sin(angle));

        axe.setxValeur(xValeur);
        axe.setyValeur(yValeur);

        axe.setxDepart(xDepart);
        axe.setyDepart(yDepart);

        axe.setxArrivee(xArrivee);
        axe.setyArrivee(yArrivee);

        axe.setxLabel(xLabel);
        axe.setyLabel(yLabel);

        axe.setxLabelvMin(xLabelvMin);
        axe.setyLabelvMin(yLabelvMin);

        axe.setxLabelvMax(xLabelvMax);
        axe.setyLabelvMax(yLabelvMax);
    }

    public void dessinerAxe(Graphics g, KiviattAxe axe) {
        g.setColor(Color.blue);
        g.drawLine(axe.getxDepart(), axe.getyDepart(), axe.getxArrivee(), axe.getyArrivee());
        g.drawString(String.valueOf(axe.getvMin()), axe.getxLabelvMin(), axe.getyLabelvMin());
        g.drawString(String.valueOf(axe.getvMax()), axe.getxLabelvMax(), axe.getyLabelvMax());

        int xValeurCentree = axe.getxValeur() - SIZE_POINT / 2;
        int yValeurCentree = axe.getyValeur() - SIZE_POINT / 2;

        g.fillOval(xValeurCentree, yValeurCentree, SIZE_POINT, SIZE_POINT);
        g.drawString(axe.getNom(), axe.getxLabel(), axe.getyLabel());
        g.setColor(Color.red);
        g.drawString(String.valueOf(axe.getValeur()), xValeurCentree, yValeurCentree);
    }

    private void dessinerTraitsEtSurfaces(Graphics g) {
        Polygon polyMiddle = new Polygon();
        for (int i = 1; i < listAxes.size(); i++) {
            Polygon surface = createSurfaceBetweenAxes(listAxes.get(i - 1), listAxes.get(i));
            g.setColor(Color.CYAN);
            g.fillPolygon(surface);
            g.setColor(Color.red);
            g.drawLine(listAxes.get(i - 1).getxValeur(), listAxes.get(i - 1).getyValeur(), listAxes.get(i).getxValeur(), listAxes.get(i).getyValeur());
            polyMiddle.addPoint(listAxes.get(i).getxDepart(), listAxes.get(i).getyDepart());
        }
        polyMiddle.addPoint(listAxes.get(0).getxDepart(), listAxes.get(0).getyDepart());
        Polygon surface = createSurfaceBetweenAxes(listAxes.get(listAxes.size() - 1), listAxes.get(0));
        g.setColor(Color.CYAN);
        g.fillPolygon(surface);
        g.setColor(Color.red);
        g.drawLine(listAxes.get(listAxes.size() - 1).getxValeur(), listAxes.get(listAxes.size() - 1).getyValeur(), listAxes.get(0).getxValeur(), listAxes.get(0).getyValeur());
        g.drawPolygon(polyMiddle);
    }

    private Polygon createSurfaceBetweenAxes(KiviattAxe axeAvant, KiviattAxe axeApres) {
        Polygon surface = new Polygon();
        surface.addPoint(axeAvant.getxDepart(), axeAvant.getyDepart());
        surface.addPoint(axeAvant.getxValeur(), axeAvant.getyValeur());
        surface.addPoint(axeApres.getxValeur(), axeApres.getyValeur());
        surface.addPoint(axeApres.getxDepart(), axeApres.getyDepart());
        return surface;
    }

    private void setListFromModel() {
        listAxes.clear();
        for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
            KiviattAxe axe = new KiviattAxe();
            axe.setNom((String) tableModel.getValueAt(rowIndex, 0));
            axe.setIndex(rowIndex);
            axe.setValeur((Integer) tableModel.getValueAt(rowIndex, 1));
            axe.setvMin((Integer) tableModel.getValueAt(rowIndex, 2));
            axe.setvMax((Integer) tableModel.getValueAt(rowIndex, 3));
            listAxes.add(axe);
        }
        double subdiv = 2 * Math.PI / listAxes.size();
        double angle = 0;
        for (KiviattAxe axe : listAxes) {
            calculsAxe(axe, angle);
            angle += subdiv;
        }
        repaint();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        setListFromModel();
    }
}
