package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jpanelView.TetrisView;
import mainPackage.UserAction;
import tetrisModel.TetrisPlayerModel;
import tetrisModel.UserMap;

public class TetrisController{
	
	List<TetrisPlayerModel> players = new ArrayList<TetrisPlayerModel>();
	
	int controllerState;
	Timer timer;
	TetrisView view;
	
	public TetrisController(TetrisView _view)
	{
		this.view = _view;
		this.timer = new Timer();
		this.controllerState = 1;
		timer.schedule(new TimerTask()
				{
					public void run()
					{
						if(controllerState == 0)
						{
							
							for(TetrisPlayerModel player: players)
							{
								player.Down();
							}
						}
					}
				}, 0, 100);
	}
	
	public void registerPlayer(TetrisPlayerModel player)
	{
		players.add(player);
	}

	public void UpdateUserAction(UserAction action)
	{
		switch(action) {
		case Reset:
			for(TetrisPlayerModel tmpPlayer: players)
			{
				tmpPlayer.Reset();
			}
			this.controllerState = 2;
			break;
			
		case ParseOrStart:
			this.controllerState = 1;
			break;
		default:
			throw new IllegalArgumentException("test");
		}
	}
	
	public void UpdateUserAction(int playerId, UserAction action)
	{
		if(playerId == -1)
		{
			for(TetrisPlayerModel player : players)
			{
				TakeAction(player, action);
			}
		}
		
		if(playerId < 0 || playerId >= players.size())
		{
			return;
		}

		TakeAction(players.get(playerId), action);
	}
	
	private void TakeAction(TetrisPlayerModel player, UserAction action)
	{	
		switch(action){
		case Up:
			player.Up();
			break;
		case Down:
			player.Down();
			break;
		case Left:
			player.Left();
			break;
		case Right:
			player.Right();
			break;
		case Reset:
			// TODO:
			break;
		default:
			throw new IllegalArgumentException("incorrect Action was sent");
		}
	}
	
	public UserMap[] getMap()
	{
		UserMap[] maps = new UserMap[players.size()];
		for(int i = 0 ; i < players.size(); i ++)
		{
			maps[i] = players.get(i).getUserMap();
		}
		
		return maps;
	}
	
	public int GetControllerState() {
		return this.controllerState;
	}
	
	public void PauseOrStart()
	{
		if (this.controllerState < 2)
		{
			this.controllerState = 1 - this.controllerState;
		}
	}
	
	public void FireRepaint()
	{
		this.view.SetMaps(getMap(), controllerState);
		this.view.repaint();
	}
}
