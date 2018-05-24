package com.softtek.raffle.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.softtek.raffle.dto.Raffle;

@Controller
public class RaffleController {

	@GetMapping("/raffle")
	public String raffleForm(Model model) {
		model.addAttribute("raffle", new Raffle());
		return "raffle";
	}

	@PostMapping("/raffle")
	public String raffleSubmit(@ModelAttribute Raffle raffle) {

		String candidates = raffle.getCandidates();
		List<String> tickets = getCandidatesFromTextArea(candidates);
		List<String> prizes = getPrizesFromTextArea(raffle.getPrizes());
		raffle.setSummary(printSummary(prizes, candidates, tickets));

		Map<String, String> awards = raffle(prizes, tickets);
		raffle.setAwards(fromAwardsToTextArea(awards));
		return "result";
	}

	private Map<String, String> raffle(List<String> prizes,
			List<String> candidates) {

		Map<String, String> awards = new HashMap<String, String>();
		
		for (String prize : prizes) {
			String winner = getRandom(candidates);
			while (awards.containsKey(winner)) {
				winner = getRandom(candidates);
			}
			awards.put(winner, prize);
		}
		return awards;
	}

	private String getRandom(List<String> candidates) {

		Random random = new Random();
		return candidates.get(random.nextInt(candidates.size()));
	}

	public List<String> getCandidatesFromTextArea(String textArea) {

		List<String> candidates = new ArrayList<String>();
		for (String line : textArea.split("\\n")) {
			String[] split = line.split(",");

			String name = split[0].trim();
			int tickets = Integer.valueOf(split[1].trim());

			while (tickets > 0) {
				candidates.add(name);
				tickets--;
			}
		}
		return candidates;
	}

	private String fromAwardsToTextArea(Map<String, String> awards) {
		String textArea = "";
		for (Map.Entry<String, String> entry : awards.entrySet()) {
			textArea += entry.getValue() + " => " + entry.getKey() + "<br/>";
		}
		return textArea;
	}

	private List<String> getPrizesFromTextArea(String textArea) {

		List<String> prizes = new ArrayList<String>();
		for (String line : textArea.split("\\n")) {
			prizes.add(line);
		}
		return prizes;
	}
	
	private String printSummary(List<String> prizes, String candidates,
			List<String> tickets) {

		int prizeSize = prizes.size();
		int candidatesSize = candidates.split("\\n").length;
		int ticketsSize = tickets.size();

		return "" + prizeSize + " PREMIOS <br/>" + candidatesSize
				+ " PARTICIPANTES <br/>" + ticketsSize + " TICKETS";
	}

}
