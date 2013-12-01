package aztec;

public class Player extends User {
	Date dateOfBirth
	Team currentTeam
	static hasMany = [reports: Report]
	static belongsTo = Team

	def trainingAttendance() {
	  def total = 0, attended = 0
	  reports.each { report ->
		  if (report.reportType == 'sessionCoach')
		    total++
			if (report.overallRating != 0)
		      attended++
	  }
	  return [attended, total]
	}

	def reportsByDate() {
		reports.asList().sort({a,b-> b.date<=>a.date});
	}
}
