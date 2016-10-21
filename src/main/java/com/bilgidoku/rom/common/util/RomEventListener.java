package com.bilgidoku.rom.common.util;

public interface RomEventListener <K> {
	public boolean romEvent(K k, int code, Object more);
}
