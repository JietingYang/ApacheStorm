

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class FileReaderSpout implements IRichSpout {
	private SpoutOutputCollector _collector;
	private TopologyContext context;
	private FileReader fr;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

		/*
		 * ----------------------TODO----------------------- Task: initialize
		 * the file reader
		 * 
		 * 
		 * -------------------------------------------------
		 */

		this.context = context;
		this._collector = collector;
		try {
			this.fr = new FileReader(conf.get("input").toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nextTuple() {

		/*
		 * ----------------------TODO----------------------- Task: 1. read the
		 * next line and emit a tuple for it 2. don't forget to sleep when the
		 * file is entirely read to prevent a busy-loop
		 * 
		 * -------------------------------------------------
		 */
		Utils.sleep(100);
		BufferedReader reader = null;
		String sentence = null;

		try {
			reader = new BufferedReader(fr);
			while ((sentence = reader.readLine()) != null) {
				Utils.sleep(100);
				_collector.emit(new Values(sentence));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		declarer.declare(new Fields("word"));

	}

	@Override
	public void close() {
		/*
		 * ----------------------TODO----------------------- Task: close the
		 * file
		 * 
		 * 
		 * -------------------------------------------------
		 */
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}

	@Override
	public void ack(Object msgId) {
	}

	@Override
	public void fail(Object msgId) {
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}
