package es.ucm.tp1.supercars.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.view.GameSerializer;

public class Record {
	private long[] records;
	
	private String[] levelsRecorded;
	
	private static final String ERROR = "Corrupt input file \n";
	
	private static final int NUMBER_OF_LEVELS = 4;
	
	private static final String RECORD_FILE_NAME = "record.txt";
	
	private boolean validRecord;
	
	public Record() {
		records = new long[NUMBER_OF_LEVELS];
		levelsRecorded = new String[NUMBER_OF_LEVELS];
		validRecord = false;
	}
	
	public void setPreviousRecords() throws CommandExecuteException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(RECORD_FILE_NAME));
			String nextLine;
			for (int i = 0; i < NUMBER_OF_LEVELS; i++) {
				nextLine = br.readLine();
				if (nextLine != null) {
					for (int j = 0; j < nextLine.length(); j++) {
						if (nextLine.charAt(j) == ':') {
							levelsRecorded[i] = nextLine.substring(0, j);
							records[i] = Long.parseLong(nextLine.substring(j + 1));
							System.out.println(levelsRecorded[i]);
						}
					}
				}
			}
			validRecord = true;
			br.close();
		} catch (IOException e) {
			throw new InputOutputRecordException(ERROR);
		}
	}
	
	public Long getRecord(String level) {
		for (int i = 0; i < levelsRecorded.length; i++) {
			if (levelsRecorded[i] != null) {
				if (levelsRecorded[i].equalsIgnoreCase(level)) {
					return records[i];
				}
			}
		}
		return (long) 0;
	}
	
	public int indexOfLevel(String level) {
		for (int i = 0; i < levelsRecorded.length; i++) {
			if (levelsRecorded[i].equalsIgnoreCase(level)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isValid() {
		return validRecord;
	}
	
	public void setNewRecord(String level, long time) {
		int ind = indexOfLevel(level);
		if (ind != -1) {
			if (records[ind] < time) {
				records[ind] = time;
			}
		} else {
			levelsRecorded[levelsRecorded.length] = level;
			records[records.length] = time;
		}
	}
	
	public void writeNewRecord() {
		try (Writer writer = new BufferedWriter(new FileWriter(RECORD_FILE_NAME))) {
			for(int i = 0; i < levelsRecorded.length; i++) {
				writer.write(levelsRecorded[i]);
				writer.write(":");
				writer.write(records[i] + "\n");
			}
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		}
	}
}
