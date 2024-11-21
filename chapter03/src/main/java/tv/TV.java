package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}

	public void power(boolean b) {
		this.power = b;
	}

	public void status() {
		System.out.println("TV[channel=" + channel + ", volume=" + volume + ", power=" + (power ? "on" : "off") + "]");
	}

	public void volume(int i) {
		if (i < 0) {
			this.volume = 100;
		} else if (i > 100) {
			this.volume = 0;
		} else {
			this.volume = i;
		}
	}

	public void volume(boolean b) {
		if (b == true) {
			if (this.volume == 100) {
				volume = 1;
			} else {
				volume++;
			}
		} else {
			if (this.volume == 0) {
				volume = 100;
			} else {
				volume--;
			}
		}
	}

	public void channel(int i) {
		if (i < 1) {
			this.channel = 255;
		} else if (i > 255) {
			this.channel = 1;
		} else {
			this.channel = i;
		}
	}

	public void channel(boolean b) {
		if (b == true) {
			if (this.channel == 255) {
				channel = 1;
			} else {
				channel++;
			}
		} else {
			if (this.channel == 1) {
				channel = 255;
			} else {
				channel--;
			}
		}
	}

}
