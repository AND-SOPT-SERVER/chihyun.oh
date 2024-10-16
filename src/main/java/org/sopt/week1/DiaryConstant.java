package org.sopt.week1;

public enum DiaryConstant {
	BODY_LENGTH_UPPER_LIMIT(30),
	PATCH_COUNT_DEFAULT(0),
	PATCH_COUNT_UPPER_LIMIT(2);

	private final int value;

	DiaryConstant(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
