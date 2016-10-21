package com.bilgidoku.rom.common.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.bilgidoku.rom.common.err.KnownError;

public class Shell {

	
	public static String safeExec(String... params){
		ProcessBuilder pb = new ProcessBuilder(params);
		String output;
		try {
			Process p = pb.start();
			output = IOUtils.toString(pb.start().getInputStream(), Charset.defaultCharset());
			p.waitFor();
			return output;
		} catch (IOException | InterruptedException e) {
			return "error";
		}
	}
	
	public static String execIgnoreExitCode(String... params) throws KnownError {
		ProcessBuilder pb = new ProcessBuilder(params);
		String output;
		try {
			Process p = pb.start();
			output = IOUtils.toString(pb.start().getInputStream(), Charset.defaultCharset());
			p.waitFor();
			return output;
		} catch (IOException | InterruptedException e) {
			throw new KnownError("Process exec failed:" + StringUtils.join(params, " "), e);

		}
	}
	
	public static String exec(String... params) throws KnownError {
		ProcessBuilder pb = new ProcessBuilder(params);
		String output;
		try {
			Process p = pb.start();
			output = IOUtils.toString(pb.start().getInputStream(), Charset.defaultCharset());
			p.waitFor();
			if (p.exitValue() == 0) {
				return output;
			}
			throw new KnownError("Process exec failed:" + StringUtils.join(params, " ") + " Output:" + output);

		} catch (IOException | InterruptedException e) {
			throw new KnownError("Process exec failed:" + StringUtils.join(params, " "), e);

		}
	}
	
	public static void main(String[] args) throws KnownError {
		System.out.println(Shell.exec("ls -sk"));
//		Shell.exec("ls");
	}
}
