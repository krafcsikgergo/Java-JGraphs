package view;

import model.Graph;
import model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Drawing extends JPanel {
    LinkedHashMap<Node, Coordinate> points = new LinkedHashMap<>();
    Graph graph;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // drawing nodes
        for (Node n : points.keySet()) {
            g2d.setColor(Color.black);
            Ellipse2D.Double circle = new Ellipse2D.Double(points.get(n).X, points.get(n).Y, 10, 10);
            g2d.fill(circle);
            g2d.drawString(n.toString(), points.get(n).X + 20, points.get(n).Y -5);
            // drawing edges
            for (Node m : graph.getNeighbours(n)){
                g2d.setColor(Color.BLUE);
                g2d.drawLine(points.get(m).X + 5, points.get(m).Y + 5, points.get(n).X + 5, points.get(n).Y + 5);
                g2d.drawString(String.valueOf(graph.getWeight(m, n)), (points.get(m).X + points.get(n).X)/2 + 20, (points.get(m).Y + points.get(n).Y)/2 + 20);
            }
        }


    }
    public void update(Graph g){
        graph = g;
        double d = Math.PI*2 / graph.sizeofPoints();
        Object[] nodes = graph.getNodes().toArray();
        points = new LinkedHashMap<>();
        for(int i = 0; i < graph.sizeofPoints(); i++){
            points.put((Node)nodes[i],new Coordinate(parametricX(d*i), parametricY(d*i)));
        }
    }
    public Integer parametricX(double angle){
        Double d = 400 + (300 * Math.cos(angle));
        return d.intValue();
    }
    public Integer parametricY(double angle){
        Double d = 350 + (300 * Math.sin(angle));
        return d.intValue();
    }
}
