import SwiftUI
import shared

struct ContentView: View {
    let greeting = Greeting()
	var body: some View {
		Text(greeting.sayHello())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
