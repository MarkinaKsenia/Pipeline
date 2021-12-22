package com.markinaksenia.pipeline;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	public void create () {
		this.setScreen(new Main(this,1,1));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
	}
}
