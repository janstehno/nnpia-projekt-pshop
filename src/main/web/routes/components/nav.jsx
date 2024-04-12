function Nav() {
    return (
        <nav>
            <a id="title" href="/"><h3>Pshop</h3></a>
            <div id="links">
                <a href="/cameras">Fotoaparáty</a>
                <a href="/lenses">Objektivy</a>
                <a href="/tripods">Stativy</a>
                <span id="divider" />
                <a href="/sign-up">Registrace</a>
                <a href="/sign-in">Přihlášení</a>
            </div>
        </nav>
    );
}

export default Nav