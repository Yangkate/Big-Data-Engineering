package week3.mapreduce;

import java.util.StringTokenizer;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;

public class WordCount {

	public static void main(final String[] args) throws Exception {
		final Configuration configuration = new Configuration();
		final Job job = Job.getInstance(configuration, "Word Count");
		// this is job set
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumCombiner.class);
		job.setReducerClass(IntSumReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat
				.addInputPath(
						job,
						new Path(
								"s3://data-for-stream-counting-5e33f2e9-bb84-4c40-bb03-5bb086c7adea/data100000"));
		FileOutputFormat
				.setOutputPath(
						job,
						new Path(
								"s3://jyu-ties438-jiayang-c78b305c-4838-4b17-ab87-5407eb4caf6d/out/17"));
		// this is to sum different output file and sum it and create a distinct
		// file to show
		if (job.waitForCompletion(true)) {
			final Path path = new Path(
					"s3://jyu-ties438-jiayang-c78b305c-4838-4b17-ab87-5407eb4caf6d/out/17");
			final FileSystem filesystem = path.getFileSystem(configuration);
			long sum = 0L;
			final FileStatus[] statuses = filesystem.listStatus(path);// list
																		// sub
																		// folder
																		// in
																		// out
																		// 17
			for (int i = 0; i < statuses.length; ++i) {
				final FileStatus status = statuses[i];
				sum += status.getLen();
			}
			final Path output = new Path(path.toUri() + "/distinct.txt");// output
																				// file
																				// with
																				// distinct
																				// number
			final FSDataOutputStream out = filesystem.create(output);
			final PrintWriter writer = new PrintWriter((OutputStream) out);
			writer.write("distinct: " + sum / 2L);
			System.exit(0);
		}
		System.exit(1);
	}

	// use combiner to combine key with sum of value
	public static class IntSumCombiner extends
			Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (final IntWritable val : values) {
				sum += val.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	// use reducer to show different word with number 1
	public static class IntSumReducer extends
			Reducer<Text, IntWritable, IntWritable, NullWritable> {
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			context.write(new IntWritable(1), NullWritable.get());
		}
	}

	// this is mapper to split
	public static class TokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {
		private static final IntWritable one = new IntWritable(1);;
		private Text word = new Text();;

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			final StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				this.word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}
}
