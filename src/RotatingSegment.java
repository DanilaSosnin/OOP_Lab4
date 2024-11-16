import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingSegment extends JPanel implements ActionListener {
    private double angle = 0; // Угол вращения
    private final int length = 100; // Длина отрезка
    private final Timer timer; // Таймер для анимации
    private double position = 2; // Позиция точки
    private double speed = 2.1; // Скорость движения точки
    private int center_x, center_y; // Координаты точки
    private int x1, x2, y1, y2; //Координаты концов отрезка

    public RotatingSegment() {
        timer = new Timer(20, this); // Таймер
        timer.start(); // Запуск таймера
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        x2 = getWidth();
        x1 = 0;
        y2 = getHeight();
        y1 = 0;

        center_x = center_x == 0? getWidth() / 2 : (int)(Math.abs(x2 - x1) / 2 + position * Math.cos(angle));
        center_y = center_y == 0? getHeight() / 2 : (int)(Math.abs(y2 - y1) / 2 + position * Math.sin(angle));

        //Вычисляем координаты отрезка
        x2 = (int) (center_x + (length + position) * Math.cos(angle));
        y2 = (int) (center_y + (length + position) * Math.sin(angle));
        x1 = (int) (center_x - (length - position) * Math.cos(angle));
        y1 = (int) (center_y - (length - position) * Math.sin(angle));

        // Меняем позицию и направление точки
        position += speed;
        if (position <= -length || position >= length) speed *= -1;

        // Рисуем отрезок
        g2d.drawLine(x1, y1, x2, y2);

        // Рисуем точку на конце отрезка
        g2d.fillOval(center_x - 5, center_y - 5, 10, 10); // Точка радиусом 5
    }

    public void actionPerformed(ActionEvent e) {
        angle += Math.toRadians(5); // Увеличиваем угол на 5 градусов
        repaint(); // Перерисовываем панель
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Segment");
        RotatingSegment panel = new RotatingSegment();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}