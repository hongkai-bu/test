package com.hongkai.start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static com.hongkai.start.Start.frameWindow;

public class Add {
    private JButton buttonAdd;
    private JPanel jpaneladd;
    private JProgressBar progressBar1;
    private JButton buttonAdd2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JFileChooser fileChooserAdd;

    public JFrame frameAdd;

    public Add(){
        frameAdd=new JFrame("新增");
        ImageIcon imageIcon1=new ImageIcon(ClassLoader.getSystemResource("png").getPath()+"/add.png");
        frameAdd.setIconImage(imageIcon1.getImage());
        frameAdd.setContentPane(jpaneladd);
        frameAdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameAdd.pack();
        frameAdd.setVisible(true);
        frameAdd.setSize(600,400);
        frameAdd.setLocationRelativeTo(null);

        fileChooserAdd=new JFileChooser();
        fileChooserAdd.setMultiSelectionEnabled(true);
        fileChooserAdd.setFileSelectionMode(JFileChooser.FILES_ONLY);
        event();
    }

    private void event() {
        frameAdd.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frameWindow.setEnabled(true);
            }
        });

        buttonAdd.addActionListener((ActionEvent e)->{
            Task task = new Task();
            task.start();
            buttonAdd.setEnabled(false);
        });

        buttonAdd2.addActionListener(e->{
            fileChooserAdd.showOpenDialog(null);
            File[] files=fileChooserAdd.getSelectedFiles();
            StringBuffer content=new StringBuffer();
            for(File afile:files){
                if(!content.toString().isEmpty()){
                    content.append("\n");
                }
                content.append(afile.getPath());
            }
            textArea1.append(content.toString());
        });

    }

    private class Task extends Thread {

        public Task() {
            progressBar1.setVisible(true);
        }

        public void run() {
            for (int i = 0; i <= 100; i += 10) {
                final int progress = i;

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar1.setValue(progress);
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
            JOptionPane.showMessageDialog(jpaneladd, "处理完成", "提示",JOptionPane.INFORMATION_MESSAGE);
            buttonAdd.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new Add();
    }
}
