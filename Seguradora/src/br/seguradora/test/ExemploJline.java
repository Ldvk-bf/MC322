package br.seguradora.test;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp.Capability;

public class ExemploJline {
    public static void main(String[] args) throws Exception {

    	
    	Terminal terminal = TerminalBuilder.terminal();

    	

    	terminal.puts(Capability.cursor_address, 50, 50);
    	terminal.writer().print("Hello World");
    	terminal.flush();



    }
}
