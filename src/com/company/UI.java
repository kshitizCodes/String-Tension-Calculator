package com.company;

import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class UI extends JFrame implements ActionListener {
    JFrame frame;
    JPanel type, first, second, third, fourth, fifth, sixth, button;
    JLabel string1, string2, string3, string4, string5, string6;
    JTextField scaleLengthFirst, scaleLengthSecond, scaleLengthThird, scaleLengthFourth, scaleLengthFifth, scaleLengthSixth;
    JComboBox<String> pitchInputFirst, pitchInputSecond, pitchInputThird, pitchInputFourth, pitchInputFifth, pitchInputSixth, typeInput;
    JComboBox<String> gaugeFirst, gaugeSecond, gaugeThird, gaugeFourth, gaugeFifth, gaugeSixth;
    JButton submit;

    String[] arrPitch = {"E4", "B3", "G3", "D3", "A2", "E2"};
    String[] arrType = {"Plain Steel", "Phosphore Bronze Wound", "Nickel Wound", "Stainless Steel Wound", "Half Round Wound",
            "Chromes - Stainless Steel Flat Wound", "Flat Tops - Phosphore Bronze Polished", "80-20 S- 80/20 Brass Round Wound",
            "Great American Bronze - 810/110 Brass Round W", "Bass - Nickplated Round Wound", "Bass - Half Round - Pure Nickel Half Round",
            "Bass - Chromes - Stainless Steel Flat Wound", "Bass - ProSteels - ProSteel Round Wound"
    };

    ArrayList<String> names;
    String[] nameArr;

    Database db = new Database();
    Connection conn = db.openConnection();
    Statement stmt = null;
    ResultSet rs;

    public UI() {
        frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setTitle("String Tension Calculator");

        type = new JPanel();
        type.setLayout(new FlowLayout());
        typeInput = new JComboBox<>(arrType);
        typeInput.setSelectedIndex(0);
        type.add(typeInput);
        frame.add(type);

        names = new ArrayList<>();
        try {
            String type = Objects.requireNonNull(typeInput.getSelectedItem()).toString();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT gauge FROM " + new StringType(type).getStringType());
            while (rs.next()) {
                names.add(rs.getString(1));
            }
            nameArr = new String[names.size()];
            nameArr = names.toArray(nameArr);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        gaugeFirst = new JComboBox<>(nameArr);
        gaugeSecond = new JComboBox<>(nameArr);
        gaugeThird = new JComboBox<>(nameArr);
        gaugeFourth = new JComboBox<>(nameArr);
        gaugeFifth = new JComboBox<>(nameArr);
        gaugeSixth = new JComboBox<>(nameArr);


        typeInput.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        String type = Objects.requireNonNull(typeInput.getSelectedItem()).toString();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT gauge FROM " + new StringType(type).getStringType());
                        gaugeFirst.removeAllItems();
                        gaugeSecond.removeAllItems();
                        gaugeThird.removeAllItems();
                        gaugeFourth.removeAllItems();
                        gaugeFifth.removeAllItems();
                        gaugeSixth.removeAllItems();
                        while (rs.next()) {
                            String string = rs.getString(1);
                            gaugeFirst.addItem(string);
                            gaugeSecond.addItem(string);
                            gaugeThird.addItem(string);
                            gaugeFourth.addItem(string);
                            gaugeFifth.addItem(string);
                            gaugeSixth.addItem(string);
                        }
                    } catch (SQLException | NullPointerException err) {
                        err.printStackTrace();
                    }
                    first.add(gaugeFirst);
                    second.add(gaugeSecond);
                    third.add(gaugeThird);
                    fourth.add(gaugeFourth);
                    fifth.add(gaugeFifth);
                    sixth.add(gaugeSixth);
                }
            }
        });

        first = new JPanel();
        string1 = new JLabel("String 1");
        scaleLengthFirst = new JTextField(10);
        PromptSupport.setPrompt(" Scale length", scaleLengthFirst);
        pitchInputFirst = new JComboBox<>(arrPitch);
        pitchInputFirst.setSelectedIndex(0);
        first.add(string1);
        first.add(scaleLengthFirst);
        first.add(pitchInputFirst);
        frame.add(first);

        second = new JPanel();
        string2 = new JLabel("String 2");
        scaleLengthSecond = new JTextField(10);
        PromptSupport.setPrompt(" Scale length", scaleLengthSecond);
        pitchInputSecond = new JComboBox<>(arrPitch);
        pitchInputSecond.setSelectedIndex(1);
        second.add(string2);
        second.add(scaleLengthSecond);
        second.add(pitchInputSecond);
        frame.add(second);

        third = new JPanel();
        string3 = new JLabel("String 3");
        scaleLengthThird = new JTextField(10);
        PromptSupport.setPrompt(" Scale length", scaleLengthThird);
        pitchInputThird = new JComboBox<>(arrPitch);
        pitchInputThird.setSelectedIndex(2);
        third.add(string3);
        third.add(scaleLengthThird);
        third.add(pitchInputThird);
        frame.add(third);

        fourth = new JPanel();
        string4 = new JLabel("String 4");
        scaleLengthFourth = new JTextField(10);
        PromptSupport.setPrompt(" Scale length", scaleLengthFourth);
        pitchInputFourth = new JComboBox<>(arrPitch);
        pitchInputFourth.setSelectedIndex(3);
        fourth.add(string4);
        fourth.add(scaleLengthFourth);
        fourth.add(pitchInputFourth);
        frame.add(fourth);
        fifth = new JPanel();
        string5 = new JLabel("String 5");
        scaleLengthFifth = new JTextField(10);
        PromptSupport.setPrompt(" Scale Length", scaleLengthFifth);
        pitchInputFifth = new JComboBox<>(arrPitch);
        pitchInputFifth.setSelectedIndex(4);
        fifth.add(string5);
        fifth.add(scaleLengthFifth);
        fifth.add(pitchInputFifth);
        frame.add(fifth);
        sixth = new JPanel();
        string6 = new JLabel("String 6");
        scaleLengthSixth = new JTextField(10);
        PromptSupport.setPrompt(" Scale length", scaleLengthSixth);
        pitchInputSixth = new JComboBox<>(arrPitch);
        pitchInputSixth.setSelectedIndex(5);
        sixth.add(string6);
        sixth.add(scaleLengthSixth);
        sixth.add(pitchInputSixth);
        frame.add(sixth);
        button = new JPanel();
        submit = new JButton("Submit");
        submit.addActionListener(this);
        button.add(submit);
        frame.add(button);

        first.add(gaugeFirst);
        second.add(gaugeSecond);
        third.add(gaugeThird);
        fourth.add(gaugeFourth);
        fifth.add(gaugeFifth);
        sixth.add(gaugeSixth);

        frame.requestFocusInWindow();
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String type = Objects.requireNonNull(this.typeInput.getSelectedItem()).toString();
            String pitch = Objects.requireNonNull(this.pitchInputFirst.getSelectedItem()).toString();
            double scaleLengthFirst = Double.parseDouble(this.scaleLengthFirst.getText());
            double gaugeFirst = Double.parseDouble(Objects.requireNonNull(this.gaugeFirst.getSelectedItem()).toString());

            Calculator calculator1 = new Calculator(scaleLengthFirst, gaugeFirst, pitch, type);

            Calculator calculator2 = new Calculator(Double.parseDouble(this.scaleLengthSecond.getText()),
                    Double.parseDouble(Objects.requireNonNull(this.gaugeSecond.getSelectedItem()).toString()),
                    Objects.requireNonNull(this.pitchInputSecond.getSelectedItem()).toString(),
                    type
            );
            Calculator calculator3 = new Calculator(Double.parseDouble(this.scaleLengthThird.getText()),
                    Double.parseDouble(Objects.requireNonNull(this.gaugeThird.getSelectedItem()).toString()),
                    Objects.requireNonNull(this.pitchInputThird.getSelectedItem()).toString(),
                    type
            );
            Calculator calculator4 = new Calculator(Double.parseDouble(this.scaleLengthFourth.getText()),
                    Double.parseDouble(Objects.requireNonNull(this.gaugeFourth.getSelectedItem()).toString()),
                    Objects.requireNonNull(this.pitchInputFourth.getSelectedItem()).toString(),
                    type
            );
            Calculator calculator5 = new Calculator(Double.parseDouble(this.scaleLengthFifth.getText()),
                    Double.parseDouble(Objects.requireNonNull(this.gaugeFifth.getSelectedItem()).toString()),
                    Objects.requireNonNull(this.pitchInputFifth.getSelectedItem()).toString(),
                    type
            );
            Calculator calculator6 = new Calculator(Double.parseDouble(this.scaleLengthSixth.getText()),
                    Double.parseDouble(Objects.requireNonNull(this.gaugeSixth.getSelectedItem()).toString()),
                    Objects.requireNonNull(this.pitchInputSixth.getSelectedItem()).toString(),
                    type
            );
            JFrame output = new JFrame();
            output.setLayout(new BoxLayout(output.getContentPane(), BoxLayout.PAGE_AXIS));
            output.setSize(300, 300);
            output.setTitle("String Tension");

            double result1 = calculator1.calculate();
            double result2 = calculator2.calculate();
            double result3 = calculator3.calculate();
            double result4 = calculator4.calculate();
            double result5 = calculator5.calculate();
            double result6 = calculator6.calculate();

            JPanel outputPanel1 = new JPanel();
            outputPanel1.add(new JLabel("String 1:  \t" + result1 + " lbs"));
            output.add(outputPanel1);

            JPanel outputPanel2 = new JPanel();
            outputPanel2.add(new JLabel("String 2:  \t" + result2 + " lbs"));
            output.add(outputPanel2);

            JPanel outputPanel3 = new JPanel();
            outputPanel3.add(new JLabel("String 3:  \t" + result3 + " lbs"));
            output.add(outputPanel3);

            JPanel outputPanel4 = new JPanel();
            outputPanel4.add(new JLabel("String 4:  \t" + result4 + " lbs"));
            output.add(outputPanel4);

            JPanel outputPanel5 = new JPanel();
            outputPanel5.add(new JLabel("String 5:  \t" + result5 + " lbs"));
            output.add(outputPanel5);

            JPanel outputPanel6 = new JPanel();
            outputPanel6.add(new JLabel("String 6:  \t" + result6 + " lbs"));
            output.add(outputPanel6);

            double totalResult = result1 + result2 + result3 + result4 + result5 + result6;
            JPanel totalPanel = new JPanel();
            totalPanel.add(new JLabel("Total tension:  \t" + totalResult + " lbs"));
            output.add(totalPanel);
            output.setVisible(true);
        }

    }
}
