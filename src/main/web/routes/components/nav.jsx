import NavBar from './nav-bar.jsx';

function Nav() {
    return (
        <nav>
            <NavBar />
            <div id="navigation">
                <a id="title" href="/"><h3>Pshop</h3></a>
                <div id="right">
                    <div id="links">
                        <a href="/cameras">Fotoaparáty</a>
                        <a href="/lenses">Objektivy</a>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Nav